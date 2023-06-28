package adilahsoft.jgo.android.feature_affiliate.list

import adilahsoft.jgo.android.feature_affiliate.R
import adilahsoft.jgo.android.feature_affiliate.databinding.ActivityCustomerBinding
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessLoadResult
import edts.base.core_utils.SnackBarUtils
import id.co.edtslib.baserecyclerview.BaseViewHolder
import id.co.edtslib.uibase.PopupActivity
import id.co.edtslib.util.IntentUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerActivity: PopupActivity<ActivityCustomerBinding>(), ModuleNavigator {
    private val viewModel: CustomerViewModel by viewModel()
    private val adapter = CustomerAdapter()

    private val addCustomerResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                SnackBarUtils.showSnackBar(this,
                    binding.root,
                    getString(R.string.affiliate_add_customer_success))
                loadData(true)
            }
        }

    override val bindingInflater: (LayoutInflater) -> ActivityCustomerBinding
        get() = ActivityCustomerBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setupPopup() {
        setupListener()
        setupView()
        loadData(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_customer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAdd -> {
                navigateToAddCustomerActivity(addCustomerResultLauncher)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadData(true)
        }

        binding.bvSubmit.setOnClickListener {
            navigateToAddCustomerActivity(addCustomerResultLauncher)
        }

        adapter.delegate = object : CustomerDelegate {
            override fun onCall(customerData: CustomerData) {
                if (customerData.mobile != null) {
                    IntentUtil.call(this@CustomerActivity, customerData.mobile!!)
                }
            }

            override fun onEmail(customerData: CustomerData) {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, customerData.email)
                intent.putExtra(Intent.EXTRA_SUBJECT, "")
                startActivity(intent)
            }

            override fun onMap(customerData: CustomerData) {
                navigateToMapActivity(customerData.lat, customerData.lng, null)

            }

            override fun onClick(
                t: CustomerData,
                position: Int,
                holder: BaseViewHolder<CustomerData>?
            ) {
            }

            override fun onDraw(t: CustomerData, position: Int) {
            }
        }
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadData(isReload: Boolean) {
        showShimmer()

        viewModel.get(isReload).observe(this) {
            JGoProcessLoadResult(fragmentActivity = this, result = it,
                delegate = object : JGoProcessDelegate<List<CustomerData>?> {
                    override fun success(data: List<CustomerData>?) {
                        processData(data)
                    }

                })
        }
    }

    private fun showShimmer() {
        binding.clEmpty.isVisible = false
        binding.recyclerView.isVisible = false
        binding.shimmerLayout.show()
    }

    private fun processData(data: List<CustomerData>?) {
        binding.shimmerLayout.hide()
        binding.clEmpty.isVisible = data?.isNotEmpty() != true
        binding.recyclerView.isVisible = data?.isNotEmpty() == true

        adapter.data = data
    }
}