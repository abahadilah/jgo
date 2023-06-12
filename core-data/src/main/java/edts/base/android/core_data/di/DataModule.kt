package edts.base.android.core_data.di

import edts.base.android.core_data.source.remote.network.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { provideApiService(get(named("api"))) }
    single { provideCustomerApiService(get(named("api"))) }
    single { provideOtpApiService(get(named("api"))) }
    single { provideLocationApiService(get(named("api"))) }
    single { provideStockPointApiService(get(named("api"))) }
    single { provideUploadApiService(get(named("api"))) }
    single { provideSalesRequestApiService(get(named("api"))) }
    single { provideFcmApiApiService(get(named("api"))) }
}

private fun provideApiService(retrofit: Retrofit) =
    retrofit.create(ApiService::class.java)

private fun provideCustomerApiService(retrofit: Retrofit) =
    retrofit.create(CustomerApiService::class.java)

private fun provideOtpApiService(retrofit: Retrofit) =
    retrofit.create(OtpApiService::class.java)

private fun provideLocationApiService(retrofit: Retrofit) =
    retrofit.create(LocationApiService::class.java)

private fun provideStockPointApiService(retrofit: Retrofit) =
    retrofit.create(InvoiceApiService::class.java)

private fun provideUploadApiService(retrofit: Retrofit) =
    retrofit.create(VehicleApiService::class.java)

private fun provideSalesRequestApiService(retrofit: Retrofit) =
    retrofit.create(OrderApiService::class.java)

private fun provideFcmApiApiService(retrofit: Retrofit) =
    retrofit.create(FcmApiApiService::class.java)