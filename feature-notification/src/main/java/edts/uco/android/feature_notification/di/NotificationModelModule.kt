package edts.uco.android.feature_notification.di

import edts.uco.android.feature_notification.ui.NotificationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notificationModelModule = module {
    viewModel { NotificationViewModel(get()) }

}