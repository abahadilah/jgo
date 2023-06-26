package edts.uco.android.feature_invoice.di

import edts.uco.android.feature_invoice.ui.InvoiceViewModel
import edts.uco.android.feature_invoice.ui.detail.InvoiceDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val invoiceModelModule = module {
    viewModel { InvoiceViewModel(get(), get(), get()) }
    viewModel { InvoiceDetailViewModel(get()) }

}