package edts.base.android.core_domain.di

import edts.base.android.core_domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<VehicleUseCase> { VehicleInteractor(get()) }
    factory<CustomerUseCase> { CustomerInteractor(get()) }
    factory<ProfileUseCase> { ProfileInteractor(get()) }
    factory<InvoiceUseCase> { InvoiceInteractor(get()) }
    factory<OrderUseCase> { OrderInteractor(get()) }
    factory<ConfigurationUseCase> { ConfigurationInteractor(get()) }

}