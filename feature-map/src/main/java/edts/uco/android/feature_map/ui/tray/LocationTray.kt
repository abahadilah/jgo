package edts.uco.android.feature_map.ui.tray

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_data.RemoteConfig
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.core_utils.SnackBarUtils
import edts.uco.android.feature_map.R
import edts.uco.android.feature_map.databinding.TrayLocationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.textfield.TextFieldDelegate
import timber.log.Timber

class LocationTray(private val context: Context) {
    private val binding = TrayLocationBinding.inflate(LayoutInflater.from(context))

    var delegate: LocationDelegate? = null
    private var runnable: Runnable? = null
    private var adapter = AddressAdapter()
    private var dialog: BottomLayoutDialog? = null

    init {
        binding.vEmpty.root.isVisible = false
        binding.vEmpty.bvSubmit.setOnClickListener {
            delegate?.onMap(RemoteConfig.getLatitude(), RemoteConfig.getLongitude())
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.flCurrent.setOnClickListener {
            binding.shimmerCurrentLocation.showShimmer()

            delegate?.onCurrentLocation()
        }

        binding.etSearch.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                search(input?.trim())
            }
        }

        adapter.delegate = object : BaseRecyclerViewAdapterDelegate<VehicleTypeData> {
            override fun onClick(t: VehicleTypeData, position: Int, holder: BaseViewHolder<VehicleTypeData>?) {
                /*if (t.lat != null && t.lng != null) {
                    delegate?.onMap(t.lat!!, t.lng!!)
                    Timber.e("LAT LONG ${t.lat} ${t.lng}")
                }*/
            }

            override fun onDraw(t: VehicleTypeData, position: Int) {
            }
        }

        searchOff()
    }

    private fun search(keyword: String?) {
        if (keyword?.isNotEmpty() == true) {
            showShimmer()

            if (runnable != null) {
                binding.etSearch.removeCallbacks(runnable)
            }

            runnable = Runnable {
                doSearch(keyword)
                runnable = null
            }

            binding.etSearch.postDelayed(runnable, 1200)
        }
        else {
            searchOff()
        }
    }

    private fun showShimmer() {
        binding.llResult.isVisible = true
        binding.vEmpty.root.isVisible = false
        binding.skeleton.show()
        binding.recyclerView.isVisible = false
    }

    private fun hideShimmer() {
        binding.recyclerView.isVisible = true
        binding.skeleton.hide()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<VehicleTypeData>?) {
        hideShimmer()

        adapter.list.clear()
        if (data?.isNotEmpty() == true) {
            adapter.list.addAll(data)
        }
        adapter.notifyDataSetChanged()

        binding.vEmpty.root.isVisible = data?.isNotEmpty() != true
        binding.llResult.isVisible = data?.isNotEmpty() == true
    }

    private fun doSearch(keyword: String?) {
        if (keyword?.isNotEmpty() == true) {
            delegate?.onSearch(keyword)
        }
        else {
            searchOff()
        }
    }

    private fun searchOff() {
        binding.recyclerView.isVisible = false
        binding.skeleton.isVisible = false
    }

    fun permissionFinished() {
        binding.shimmerCurrentLocation.hideShimmer()
    }

    fun show() {
        dialog = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.location_title),
            contentView = binding.root,
            height = ViewGroup.LayoutParams.MATCH_PARENT,
            themeResId = edts.base.android.core_resource.R.style.BottomLayoutWithEditDialog)
    }

    fun close() {
        dialog?.close()
    }

    fun onCurrentLocationFailed() {
        SnackBarUtils.showSnackBarForError(context, binding.root, context.getString(R.string.location_current_location_failed))
    }
}