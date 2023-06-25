package edts.base.android.core_data.di

import edts.base.android.core_data.source.*
import edts.base.android.core_data.source.local.*
import edts.base.android.core_data.source.remote.*
import edts.base.android.core_domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { CustomerRemoteDataSource(get()) }
    single { OtpRemoteDataSource(get()) }
    single { LocationRemoteDataSource(get()) }
    single { InvoiceRemoteDataSource(get()) }
    single { ProfileLocalDataSource(get()) }
    single { VehicleRemoteDataSource(get()) }
    single { OrderRemoteDataSource(get()) }
    single { OrderLocalDataSource(get()) }
    single { ConfigurationLocalDataSource(get()) }
    single { InvoiceLocalDataSource(get()) }
    single { FcmRemoteDataSource(get()) }
    single { VehicleTypeLocalDataSource(get()) }
    single { PaymentLocalDataSource(get()) }
    single { CreateOrderOriginLocalDataSource(get()) }
    single { CreateOrderDestinationLocalDataSource(get()) }
    single { NotificationLocalDataSource(get()) }

    single<IVehicleRepository> {
        VehicleRepository(
            get(),
            get(),
            get(),
            get())
    }

    single<ICustomerRepository> {
        CustomerRepository(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<IProfileRepository> {
        ProfileRepository(
            get()
        )
    }

    single<IInvoiceRepository> {
        InvoiceRepository(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<IOrderRepository> {
        OrderRepository(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<IConfigurationRepository> {
        ConfigurationRepository(
            get()
        )
    }

    single<INotificationRepository> {
        NotificationRepository(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}