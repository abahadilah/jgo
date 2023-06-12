package edts.base.android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appViewModel = module {
    viewModel { DeeplinkViewModel(get()) }

}