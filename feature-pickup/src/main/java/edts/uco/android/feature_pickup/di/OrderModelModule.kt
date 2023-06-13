package edts.uco.android.feature_pickup.di

import edts.uco.android.feature_pickup.ui.OrderViewModel
import edts.uco.android.feature_pickup.ui.detail.OrderDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val orderViewModel = module {
    viewModel { OrderViewModel(get(), get(), get()) }
    viewModel { OrderDetailViewModel(get(), get()) }

}