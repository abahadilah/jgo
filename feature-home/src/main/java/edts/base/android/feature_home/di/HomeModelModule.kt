package edts.base.android.feature_home.di

import edts.base.android.feature_home.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModelModule = module {
    viewModel { SplashViewModel(get(), get()) }

}