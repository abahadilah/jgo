package edts.uco.android.feature_order.di

import edts.uco.android.feature_order.ui.OrderViewModel
import edts.uco.android.feature_order.ui.create.CreateOrderViewModel
import edts.uco.android.feature_order.ui.detail.OrderDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val orderViewModel = module {
    viewModel { OrderViewModel(get(), get(), get(), get()) }
    viewModel { OrderDetailViewModel(get(), get()) }
    viewModel { CreateOrderViewModel(get(), get(), get()) }

}