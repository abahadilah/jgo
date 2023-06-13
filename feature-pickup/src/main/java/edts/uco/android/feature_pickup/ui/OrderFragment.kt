package edts.uco.android.feature_pickup.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessLoadResult
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.base.core_utils.formatDecimal
import edts.base.core_utils.money
import edts.uco.android.feature_pickup.R
import edts.uco.android.feature_pickup.databinding.FragmentOrderBinding
import edts.uco.android.feature_pickup.ui.status.OrderStatusFilterDelegate
import edts.uco.android.feature_pickup.ui.status.OrderStatusFilterTray
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NumberFormatException

class OrderFragment: HomeBaseFragment<FragmentOrderBinding>(), ModuleNavigator {
    private val viewModel: OrderViewModel by viewModel()
    private val adapter = OrderAdapter()

    override fun reselect() {
        loadData(false)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOrderBinding
        get() = FragmentOrderBinding::inflate

    override fun getTrackerPageName(): String? = null

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

    private fun initData() {
        viewModel.filter.postValue(OrderStatus.All)
    }

    private fun setupListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadData(true)
        }

        binding.mcvAllStatus.setOnClickListener {
            val tray = OrderStatusFilterTray(requireContext(),
                if (viewModel.filter.value == null) OrderStatus.All else viewModel.filter.value!!)
            tray.delegate = object : OrderStatusFilterDelegate {
                override fun onSubmit(selected: OrderStatus) {
                    viewModel.filter.postValue(selected)
                }
            }
            tray.show()
        }

        adapter.delegate = object : OrderDelegate {
            override fun onOrderDetail(orderData: OrderData?) {
                if (orderData?.id != null) {
                    navigateToOrderDetail(orderData)
                }
            }

            override fun onInvoiceDetail(orderData: OrderData?) {
                showInvoice(orderData)
            }
        }
    }

    private fun showInvoice(orderData: OrderData?) {
        val sInvoiceId = orderData?.invoice?.get(0)
        if (sInvoiceId != null) {
            try {
                val invoiceId: Long = sInvoiceId.toLong()
                viewModel.getInvoice(invoiceId).observe(this) {
                    UcoProcessResult(fragmentActivity = requireActivity(), result = it,
                        delegate = object : UcoProcessDelegate<InvoiceDetailData?> {
                            override fun success(data: InvoiceDetailData?) {
                                if (data != null) {

                                    navigateToInvoiceDetail(data)
                                }
                            }

                        })
                }
            }
            catch (e: NumberFormatException) {
                UcoProcessResult.showError(requireActivity(),
                    "Terjadi kesalahan")
            }
        }
    }

    private fun loadData(isReload: Boolean) {
        loadProfile()
        loadOrder(isReload)
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun loadOrder(isReload: Boolean) {
        showShimmer()

        viewModel.getOrder(isReload).observe(this) {
            UcoProcessLoadResult(fragmentActivity = requireActivity(), result = it,
                object : UcoProcessDelegate<List<OrderData>?> {
                    override fun success(data: List<OrderData>?) {
                        hideShimmer(data)

                        processData(data)
                    }
                })
        }
    }

    private fun processData(data: List<OrderData>?) {
        val count = data?.size ?: 0

        binding.tvTitle.text = getString(R.string.order_list,
            count.toDouble().formatDecimal())

        var total = 0.0
        data?.forEach {
            total += if (it.totalAmount == null) 0.0 else it.totalAmount!!
        }

        binding.tvTotal.text = total.money(requireContext())

        adapter.setData(data)

        binding.recyclerView.post {
            (binding.recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(0)
        }
    }

    private fun loadProfile() {
        viewModel.getProfile().observe(this) {
            binding.tvName.text = it?.name
        }
    }


    private fun showShimmer() {
        binding.llSkeleton.isVisible = true
        binding.llData.isVisible = false
        binding.llEmpty.isVisible = false
    }

    private fun hideShimmer(data: List<OrderData>?) {
        binding.llSkeleton.isVisible = false
        binding.llData.isVisible = data?.isNotEmpty() == true
        binding.llEmpty.isVisible = data?.isNotEmpty() != true
    }

}