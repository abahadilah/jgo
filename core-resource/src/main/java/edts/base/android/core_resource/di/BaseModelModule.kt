package edts.base.android.core_resource.di

import edts.base.android.core_resource.JGoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModelModule = module {
    viewModel { JGoViewModel(get(), get()) }

}