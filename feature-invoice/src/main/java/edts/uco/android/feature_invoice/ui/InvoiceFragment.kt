package edts.uco.android.feature_invoice.ui

import adilahsoft.jgo.android.feature_affiliate.FilterDelegate
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_data.source.local.InvoiceStatus
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessLoadResult
import edts.base.core_utils.formatDecimal
import edts.base.core_utils.money
import edts.uco.android.feature_invoice.R
import edts.uco.android.feature_invoice.databinding.FragmentInvoceBinding
import edts.uco.android.feature_invoice.ui.status.InvoiceStatusFilterDelegate
import edts.uco.android.feature_invoice.ui.status.InvoiceStatusFilterTray
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceFragment: HomeBaseFragment<FragmentInvoceBinding>(), ModuleNavigator {
    private val viewModel: InvoiceViewModel by viewModel()
    private val adapter = InvoiceAdapter()

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
        binding.filterView.delegate = object : FilterDelegate {
            override fun onSubmit(selected: CustomerData) {
                viewModel.customer.postValue(selected)
                viewModel.setCustomer(selected).observeForever {  }
            }
        }
        binding.filterView.data = viewModel.getCustomers(false)

        viewModel.customer.observe(this) {
            binding.filterView.selected = it
            loadInvoice()
        }
    }

    private fun setupView() {
        binding.filterView.isVisible = false
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

        adapter.delegate = object : InvoiceAdapterDelegate {
            override fun onDetail(invoiceData: InvoiceData?) {
                if (invoiceData != null) {
                    navigateToInvoiceDetail(invoiceData)
                }
            }
        }
    }

    private fun loadData(isReload: Boolean) {
        viewModel.isReload = isReload
        loadProfile()
    }

    private fun loadProfile() {
        viewModel.getProfile().observe(this) {
            binding.filterView.isVisible = it?.isAffiliate() == true
            if (it?.isAffiliate() == true) {
                loadCustomer()
            }
            else {
                loadInvoice()
            }
        }
    }

    private fun loadCustomer() {
        viewModel.getCustomer().observe(this) {
            viewModel.customer.postValue(it?.customer)
        }
    }

    private fun loadInvoice() {
        showShimmer()

        viewModel.getInvoice(viewModel.isReload).observe(this) {
            JGoProcessLoadResult(fragmentActivity = requireActivity(), result = it,
                object : JGoProcessDelegate<List<InvoiceData>?> {
                    override fun success(data: List<InvoiceData>?) {
                        processData(data)
                    }
                })
        }
        viewModel.isReload = false
    }

    private fun processData(data: List<InvoiceData>?) {
        hideShimmer(data)

        val count = data?.size ?: 0
        var total = 0.0
        data?.forEach {
            total += if (it.total == null) 0.0 else it.total!!
        }

        binding.tvTitle.text = getString(R.string.invoice_list_with_total,
            count.toDouble().formatDecimal())

        binding.tvTotal.text = total.money(requireContext())

        adapter.setData(data)
    }

    private fun showShimmer() {
        binding.llSkeleton.isVisible = true
        binding.llData.isVisible = false
        binding.llEmpty.isVisible = false
    }

    private fun hideShimmer(data: List<InvoiceData>?) {
        binding.llSkeleton.isVisible = false
        binding.llData.isVisible = data?.isNotEmpty() == true
        binding.llEmpty.isVisible = data?.isNotEmpty() != true
    }
}