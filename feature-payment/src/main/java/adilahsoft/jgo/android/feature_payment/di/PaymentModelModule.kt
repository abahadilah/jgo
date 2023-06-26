package adilahsoft.jgo.android.feature_payment.di

import adilahsoft.jgo.android.feature_payment.ui.PaymentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentModelModule = module {
    viewModel { PaymentViewModel(get(), get(), get(), get()) }

}