package edts.uco.android.feature_order.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessResult
import edts.base.core_utils.formatDecimal
import edts.base.core_utils.money
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.ActivityOrderDetailBinding
import edts.base.android.core_data.source.local.OrderStatus
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailActivity: PopupActivity<ActivityOrderDetailBinding>(), ModuleNavigator {
    private val viewModel: OrderDetailViewModel by viewModel()
    private val costAdapter = OrderDetailCostAdapter()
    private val destinationAdapter = OrderDestinationAdapter()

    override val bindingInflater: (LayoutInflater) -> ActivityOrderDetailBinding
        get() = ActivityOrderDetailBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setupPopup() {
        setupView()
        setupObserver()
        initData()
    }

    private fun setupView() {
        binding.costRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.costRecyclerView.adapter = costAdapter

        binding.destinationRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.destinationRecyclerView.adapter = destinationAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserver() {
        viewModel.order.observe(this) {
            binding.tvDOName.text = it?.name
            binding.tvDate.text = it?.getCreatedDate()
            binding.tvProduct.text = it?.productName
            binding.tvDimension.text = getString(R.string.order_dimension_unit,
                it?.length?.formatDecimal(),
                it?.width?.formatDecimal(),
                it?.height?.formatDecimal(),
                it?.coli?.formatDecimal())
            binding.tvWeight.text = getString(R.string.order_kg, it?.weight?.formatDecimal())
            binding.tvStatus.text = OrderStatus.getStatus(it?.state)?.toString()
            binding.tvTotal.text = it?.totalAmount?.money(this)

            binding.lbInvoice.isVisible = it?.invoice?.name?.isNotEmpty() == true
            binding.tvInvoice.text = it?.invoice?.name

            if (it?.invoice?.id != null) {
                binding.tvInvoice.setOnClickListener { _ ->
                    showInvoice(it.invoice!!.id)
                }
            }

            loadDetail()
        }

        viewModel.orderDetail.observe(this) {
            binding.lbDeliveryTitle.isVisible = it?.recipient?.isNotEmpty() == true

            destinationAdapter.list = if (it?.recipient?.isNotEmpty() == true) it.recipient!!.toMutableList()
                else mutableListOf()
            destinationAdapter.notifyDataSetChanged()

            val haveDriver = it?.vehicle?.isNotEmpty() == true && it.vehicle?.get(0)?.name?.name?.isNotEmpty() == true

            binding.lbDeliveryVehicleTitle.isVisible = haveDriver
            binding.mcvDeliveryVehicle.isVisible = haveDriver

            if (haveDriver) {
                binding.tvDriverName.text = it?.vehicle?.get(0)?.name?.name
                binding.tvDriverPhone.text = it?.vehicle?.get(0)?.phone
                binding.tvVehicle.text = it?.vehicle?.get(0)?.vehicle?.name
            }

            binding.lbDeliveryCostTitle.isVisible = it?.cost?.isNotEmpty() == true
            binding.mcvDeliveryCost.isVisible = it?.cost?.isNotEmpty() == true

            if (it?.cost?.isNotEmpty() == true) {
                costAdapter.list = it.cost!!.toMutableList()
                costAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun showInvoice(invoiceId: Long) {
        viewModel.getInvoice(invoiceId).observe(this) {
            JGoProcessResult(fragmentActivity = this, result = it,
                delegate = object : JGoProcessDelegate<InvoiceDetailData?> {
                    override fun success(data: InvoiceDetailData?) {
                        if (data != null) {

                            navigateToInvoiceDetail(data)
                        }
                    }

                })
        }
    }

    private fun loadDetail() {
        viewModel.getOrderDetail().observe(this) {
            JGoProcessResult(fragmentActivity = this, result = it,
                object : JGoProcessDelegate<OrderDetailData?> {
                    override fun success(data: OrderDetailData?) {
                        viewModel.orderDetail.postValue(data)
                    }

                })
        }
    }

    private fun initData() {
        val json = intent?.getStringExtra("order")
        if (json?.isNotEmpty() == true) {
            val order = Gson().fromJson<OrderData?>(json, object : TypeToken<OrderData?>() {}.type)
            viewModel.order.postValue(order)
        }
    }
}