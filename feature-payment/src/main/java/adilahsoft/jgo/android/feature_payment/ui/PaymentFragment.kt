package adilahsoft.jgo.android.feature_payment.ui

import adilahsoft.jgo.android.feature_payment.R
import adilahsoft.jgo.android.feature_payment.databinding.FragmentPaymentBinding
import adilahsoft.jgo.android.feature_payment.ui.status.PaymentStatusFilterDelegate
import adilahsoft.jgo.android.feature_payment.ui.status.PaymentStatusFilterTray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_domain.model.PaymentData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessLoadResult
import edts.base.core_utils.formatDecimal
import edts.base.core_utils.money
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentFragment: HomeBaseFragment<FragmentPaymentBinding>(), ModuleNavigator {
    private val viewModel: PaymentViewModel by viewModel()
    private val adapter = PaymentAdapter()

    override fun reselect() {
        loadData(false)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPaymentBinding
        get() = FragmentPaymentBinding::inflate

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
        viewModel.filter.postValue(PaymentStatus.All)
    }

    private fun setupListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadData(true)
        }

        binding.mcvAllStatus.setOnClickListener {
            val tray = PaymentStatusFilterTray(requireContext(),
                if (viewModel.filter.value == null) PaymentStatus.All else viewModel.filter.value!!)
            tray.delegate = object : PaymentStatusFilterDelegate {
                override fun onSubmit(selected: PaymentStatus) {
                    viewModel.filter.postValue(selected)
                }
            }
            tray.show()
        }
    }

    private fun loadData(isReload: Boolean) {
        showShimmer()

        viewModel.getPayments(isReload).observe(this) {
            UcoProcessLoadResult(fragmentActivity = requireActivity(), result = it,
                object : UcoProcessDelegate<List<PaymentData>?> {
                    override fun success(data: List<PaymentData>?) {
                        processData(data)
                    }
                })
        }
    }

    private fun processData(data: List<PaymentData>?) {
        hideShimmer(data)

        val count = data?.size ?: 0
        var total = 0.0
        data?.forEach {
            total += if (it.amount == null) 0.0 else it.amount!!
        }

        binding.tvTitle.text = getString(R.string.payment_list_with_total,
            count.toDouble().formatDecimal())

        binding.tvTotal.text = total.money(requireContext())

        adapter.setData(data)
    }

    private fun showShimmer() {
        binding.llSkeleton.isVisible = true
        binding.llData.isVisible = false
        binding.llEmpty.isVisible = false
    }

    private fun hideShimmer(data: List<PaymentData>?) {
        binding.llSkeleton.isVisible = false
        binding.llData.isVisible = data?.isNotEmpty() == true
        binding.llEmpty.isVisible = data?.isNotEmpty() != true
    }
}