package edts.uco.android.feature_activity.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.base.core_utils.money
import edts.uco.android.feature_activity.databinding.ActivityInvoiceDetailBinding
import edts.uco.android.feature_activity.ui.InvoiceStatus
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceDetailActivity: PopupActivity<ActivityInvoiceDetailBinding>() {
    private val viewModel: InvoiceDetailViewModel by viewModel()
    private val costAdapter = InvoiceDetailCostAdapter()

    override val bindingInflater: (LayoutInflater) -> ActivityInvoiceDetailBinding
        get() = ActivityInvoiceDetailBinding::inflate

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
        viewModel.invoice.observe(this) {
            binding.tvInvoiceName.text = it?.name
            binding.tvCreatedDate.text = it?.created
            binding.tvDueDate.text = it?.dueDate
            binding.tvUser.text = it?.userId
            binding.tvStatus.text = InvoiceStatus.getStatus(it?.state)?.toString()
            binding.tvCompany.text = it?.company
            binding.tvAMountUntaxed.text = it?.tax?.money(this)
            binding.tvResidual.text = it?.residual?.money(this)
            binding.tvTotalAmount.text = it?.total?.money(this)

            loadDetail()
        }

        viewModel.invoiceDetail.observe(this) {
            if (it?.lines?.isNotEmpty() == true) {
                costAdapter.list = it.lines!!.toMutableList()
                costAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadDetail() {
        viewModel.getInvoiceDetail().observe(this) {
            UcoProcessResult(fragmentActivity = this, result = it,
                object : UcoProcessDelegate<InvoiceDetailData?> {
                    override fun success(data: InvoiceDetailData?) {
                        viewModel.invoiceDetail.postValue(data)
                    }

                })
        }
    }

    private fun initData() {
        val json = intent?.getStringExtra("invoice")
        if (json?.isNotEmpty() == true) {
            val order = Gson().fromJson<InvoiceData?>(json, object : TypeToken<InvoiceData?>() {}.type)
            viewModel.invoice.postValue(order)
        }
    }
}