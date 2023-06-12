package edts.uco.android.feature_activity.di

import edts.uco.android.feature_activity.ui.InvoiceViewModel
import edts.uco.android.feature_activity.ui.detail.InvoiceDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val invoiceModelModule = module {
    viewModel { InvoiceViewModel(get()) }
    viewModel { InvoiceDetailViewModel(get()) }

}