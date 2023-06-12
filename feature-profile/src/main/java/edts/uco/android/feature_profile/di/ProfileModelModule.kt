package edts.uco.android.feature_profile.di

import edts.uco.android.feature_profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModelModule = module {
    viewModel { ProfileViewModel(get(), get()) }
}