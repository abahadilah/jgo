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
            drawInvoice(it)

            loadDetail()
        }

        viewModel.invoiceDetail.observe(this) {
            if (it?.lines?.isNotEmpty() == true) {
                costAdapter.list = it.lines!!.toMutableList()
                costAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun drawInvoice(it: InvoiceData?) {
        binding.tvInvoiceName.text = it?.name
        binding.tvCreatedDate.text = it?.getCreatedDateFormat()
        binding.tvDueDate.text = it?.getDueDateFormat()
        binding.tvUser.text = it?.userId
        binding.tvStatus.text = InvoiceStatus.getStatus(it?.state)?.toString()
        binding.tvCompany.text = it?.company
        binding.tvAMountUntaxed.text = it?.tax?.money(this)
        binding.tvResidual.text = it?.residual?.money(this)
        binding.tvTotalAmount.text = it?.total?.money(this)
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
            val invoiceData = Gson().fromJson<InvoiceData?>(json, object : TypeToken<InvoiceData?>() {}.type)
            viewModel.invoice.postValue(invoiceData)
        }
        else {
            val jsonInvoiceDetailData = intent?.getStringExtra("invoiceDetailData")

            val invoiceDetailData = Gson().fromJson<InvoiceDetailData?>(jsonInvoiceDetailData, object : TypeToken<InvoiceDetailData?>() {}.type)

            drawInvoice(InvoiceData(
                id = invoiceDetailData.id,
                name = invoiceDetailData.name,
                created = invoiceDetailData.created,
                state = invoiceDetailData.state,
                total = invoiceDetailData.total,
                tax = invoiceDetailData.tax,
                userId = invoiceDetailData.userId,
                residual = invoiceDetailData.residual,
                dueDate = invoiceDetailData.dueDate,
                company = invoiceDetailData.company
            ))
            viewModel.invoiceDetail.postValue(invoiceDetailData)
        }

    }
}