package edts.uco.android.feature_map.di

import edts.uco.android.feature_map.ui.MapViewModel
import edts.uco.android.feature_map.ui.price.CheckPriceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapModelModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { CheckPriceViewModel(get()) }
}