package edts.uco.android.feature_pickup.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.base.core_utils.formatDecimal
import edts.base.core_utils.money
import edts.uco.android.feature_pickup.R
import edts.uco.android.feature_pickup.databinding.ActivityOrderDetailBinding
import edts.uco.android.feature_pickup.ui.OrderStatus
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailActivity: PopupActivity<ActivityOrderDetailBinding>() {
    private val viewModel: OrderDetailViewModel by viewModel()
    private val costAdapter = OrderDetailCostAdapter()

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

            loadDetail()
        }

        viewModel.orderDetail.observe(this) {
            binding.lbDeliveryTitle.isVisible = it?.recipient?.isNotEmpty() == true
            binding.mcvDeliveryTitle.isVisible = it?.recipient?.isNotEmpty() == true
            if (it?.recipient?.isNotEmpty() == true) {

                binding.tvPickupName.text = it.recipient?.get(0)?.pickupName?.get(1).toString()
                binding.tvPickupCity.text = it.recipient?.get(0)?.pickupCity?.get(1).toString()

                binding.tvRecipientName.text =
                    it.recipient?.get(0)?.recipientName?.get(1).toString()
                binding.tvRecipientCity.text =
                    it.recipient?.get(0)?.recipientCity?.get(1).toString()

                binding.tvDistance.text = getString(
                    R.string.order_distance_unit,
                    it.recipient?.get(0)?.distance?.formatDecimal()
                )
            }

            binding.lbDeliveryVehicleTitle.isVisible = it?.vehicle?.isNotEmpty() == true
            binding.mcvDeliveryVehicle.isVisible = it?.vehicle?.isNotEmpty() == true

            if (it?.vehicle?.isNotEmpty() == true) {
                binding.tvDriverName.text = it.vehicle?.get(0)?.name?.get(1)?.toString()
                binding.tvDriverPhone.text = it.vehicle?.get(0)?.phone
                binding.tvVehicle.text = it.vehicle?.get(0)?.vehicle?.get(1)?.toString()
            }

            binding.lbDeliveryCostTitle.isVisible = it?.cost?.isNotEmpty() == true
            binding.mcvDeliveryCost.isVisible = it?.cost?.isNotEmpty() == true

            if (it?.cost?.isNotEmpty() == true) {
                costAdapter.list = it.cost!!.toMutableList()
                costAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadDetail() {
        viewModel.getOrderDetail().observe(this) {
            UcoProcessResult(fragmentActivity = this, result = it,
                object : UcoProcessDelegate<OrderDetailData?> {
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