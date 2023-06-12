package edts.uco.android.feature_activity.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessLoadResult
import edts.uco.android.feature_activity.databinding.FragmentInvoceBinding
import edts.uco.android.feature_activity.ui.status.InvoiceStatusFilterDelegate
import edts.uco.android.feature_activity.ui.status.InvoiceStatusFilterTray
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceFragment: HomeBaseFragment<FragmentInvoceBinding>(), ModuleNavigator {
    private val viewModel: InvoiceViewModel by viewModel()
    private val adapter = ActivityAdapter()

    override fun reselect() {
        loadData(false)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInvoceBinding
        get() = FragmentInvoceBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setup() {
        setupObserver()
        setupListener()
        setupView()
        initData()
    }

    private fun setupObserver() {
        viewModel.filter.observe(this) {
            binding.tvStatus.text = it.toString()
            loadData(false)
        }
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun initData() {
        viewModel.filter.postValue(InvoiceStatus.All)
    }

    private fun setupListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadData(true)
        }

        binding.mcvAllStatus.setOnClickListener {
            val tray = InvoiceStatusFilterTray(requireContext(),
                if (viewModel.filter.value == null) InvoiceStatus.All else viewModel.filter.value!!)
            tray.delegate = object : InvoiceStatusFilterDelegate {
                override fun onSubmit(selected: InvoiceStatus) {
                    viewModel.filter.postValue(selected)
                }
            }
            tray.show()
        }

        adapter.delegate = object : ActivityAdapterDelegate {
            override fun onDetail(invoiceData: InvoiceData?) {
                if (invoiceData != null) {
                    navigateToInvoiceDetail(invoiceData)
                }
            }
        }
    }

    private fun loadData(isReload: Boolean) {
        showShimmer()

        viewModel.getInvoice(isReload).observe(this) {
            UcoProcessLoadResult(fragmentActivity = requireActivity(), result = it,
                object : UcoProcessDelegate<List<InvoiceData>?> {
                    override fun success(data: List<InvoiceData>?) {
                        hideShimmer(data)
                        adapter.setData(data)
                    }
                })
        }
    }


    private fun showShimmer() {
        binding.llSkeleton.isVisible = true
        binding.recyclerView.isVisible = false
        binding.llEmpty.isVisible = false
    }

    private fun hideShimmer(data: List<InvoiceData>?) {
        binding.llSkeleton.isVisible = false
        binding.recyclerView.isVisible = data?.isNotEmpty() == true
        binding.llEmpty.isVisible = data?.isNotEmpty() != true
    }
}