package edts.base.android

import android.app.Application
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import com.base.android.BuildConfig
import edts.base.android.core_data.di.dataModule
import edts.base.android.core_data.di.repositoryModule
import edts.base.android.core_domain.di.useCaseModule
import edts.base.android.core_resource.R
import edts.base.android.core_resource.UcoActivity
import edts.base.android.core_resource.di.baseModelModule
import edts.base.android.feature_auth.di.authModelModule
import edts.base.android.feature_home.di.homeModelModule
import edts.uco.android.feature_activity.di.invoiceModelModule
import edts.uco.android.feature_map.di.mapModelModule
import edts.uco.android.feature_pickup.di.orderViewModel
import edts.uco.android.feature_profile.di.profileModelModule
import id.co.edtslib.EdtsKu
import id.co.edtslib.edtsds.popup.Popup
import id.co.edtslib.edtsds.popup.PopupDelegate
import id.co.edtslib.tracker.Tracker
import id.co.edtslib.util.CommonUtil
import id.co.edtslib.util.IntentUtil
import id.co.edtslibcheckappversion.CheckAppVersion
import id.co.edtslibcheckappversion.data.CheckAppVersionDelegate
import id.co.edtslibcheckappversion.data.VersionItem
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        EdtsKu.packageName = BuildConfig.APPLICATION_ID
        EdtsKu.debugging = BuildConfig.DEBUG
        EdtsKu.versionName = BuildConfig.VERSION_NAME
        EdtsKu.sslPinner = CommonUtil.hexToAscii(BuildConfig.SSL_PINNING)
        EdtsKu.sslDomain = CommonUtil.hexToAscii(BuildConfig.SSL_DOMAIN)

        CheckAppVersion.debugging = BuildConfig.DEBUG
        CheckAppVersion.sslPinner = CommonUtil.hexToAscii(BuildConfig.SSL_PINNING)
        CheckAppVersion.sslDomain = CommonUtil.hexToAscii(BuildConfig.SSL_DOMAIN)
        CheckAppVersion.path = "supplier"

        Tracker.debugging = BuildConfig.DEBUG
        Tracker.appVersion = BuildConfig.VERSION_NAME

        EdtsKu.init(
            application = this,
            baseUrlApi = CommonUtil.hexToAscii(BuildConfig.BASE_API_URL),
            refreshTokenPath = CommonUtil.hexToAscii(BuildConfig.REFRESH_TOKEN_PATH),
            modules = listOf(
                appViewModel,
                dataModule,
                repositoryModule,
                useCaseModule,
                authModelModule,
                mapModelModule,
                orderViewModel,
                homeModelModule,
                profileModelModule,
                baseModelModule,
                invoiceModelModule
            )
        ) {

            Tracker.init(
                CommonUtil.hexToAscii(BuildConfig.TRACKER_API_URL),
                CommonUtil.hexToAscii(BuildConfig.TRACKER_API_KEY), it
            )

            val checkAppVersionUrl = "${CommonUtil.hexToAscii(BuildConfig.BASE_API_URL)}/api/mobile/app-version/ANDROID/"

            CheckAppVersion.intervalCached = 3600*1000
            CheckAppVersion.init(
                checkAppVersionUrl, it,
                object : CheckAppVersionDelegate {
                    override fun onAppMustUpdate(fragmentActivity: FragmentActivity,
                                                 message: String?, serverVersion: VersionItem?) {

                        showUpdateDialog(fragmentActivity = fragmentActivity,
                            serverVersion = serverVersion,
                            forceUpdate = true)
                    }

                    override fun onAppOptionalUpdate(fragmentActivity: FragmentActivity,
                                                     message: String?, serverVersion: VersionItem?) {
                        showUpdateDialog(fragmentActivity = fragmentActivity,
                            serverVersion = serverVersion,
                            forceUpdate = false)
                    }

                    override fun onAppVersionLatest(fragmentActivity: FragmentActivity,
                                                    message: String?, serverVersion: VersionItem?) {
                    }

                    override fun onError(
                        fragmentActivity: FragmentActivity,
                        code: String?,
                        message: String?
                    ) {

                    }

                    override fun onLoading(fragmentActivity: FragmentActivity) {
                    }

                    override fun onUnAuthorize(fragmentActivity: FragmentActivity) {
                    }

                })

        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun showUpdateDialog(fragmentActivity: FragmentActivity,
                                 serverVersion: VersionItem?, forceUpdate: Boolean) {
        if (! forceUpdate) {
            if (fragmentActivity is UcoActivity<*>) {
                fragmentActivity.ucoViewModel.getConfiguration().observe(fragmentActivity) {
                    if (it?.skipVersion != serverVersion?.version) {
                        doShowUpdateDialog(fragmentActivity = fragmentActivity,
                            serverVersion = serverVersion,
                            forceUpdate = false)
                    }
                }
            }

        }
        else {
            doShowUpdateDialog(fragmentActivity = fragmentActivity,
                serverVersion = serverVersion,
                forceUpdate = true)
        }

    }

    private fun doShowUpdateDialog(fragmentActivity: FragmentActivity,
                                   serverVersion: VersionItem?, forceUpdate: Boolean) {
        val popup = Popup.show(activity = fragmentActivity,
            title = fragmentActivity.getString(R.string.update_your_application),
            message = fragmentActivity.getString(R.string.update_your_application_desc,
                serverVersion?.version),
            positiveButton = fragmentActivity.getString(R.string.update),
            positiveClickListener = object : PopupDelegate {
                override fun onClick(popup: Popup, view: View) {
                    IntentUtil.openPlayStore(context = fragmentActivity,
                            packageName = BuildConfig.APPLICATION_ID)
                }
            }, dismissible = ! forceUpdate)

        popup.setOnDismissListener {
            if (fragmentActivity is UcoActivity<*>) {
                fragmentActivity.ucoViewModel.skipVersion(serverVersion?.version).observeForever {  }
            }
        }
    }

}