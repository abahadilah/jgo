package edts.base.android.feature_auth.di

import edts.base.android.feature_auth.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModelModule = module {
    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }

}