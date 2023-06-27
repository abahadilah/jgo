package adilahsoft.jgo.android.feature_affiliate.di

import adilahsoft.jgo.android.feature_affiliate.list.CustomerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerModelModule = module {
    viewModel { CustomerViewModel(get()) }

}