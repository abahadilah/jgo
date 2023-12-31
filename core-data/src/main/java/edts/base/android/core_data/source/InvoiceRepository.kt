package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.InvoiceMapper
import edts.base.android.core_data.source.local.InvoiceLocalDataSource
import edts.base.android.core_data.source.local.PaymentLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.remote.InvoiceRemoteDataSource
import edts.base.android.core_data.source.remote.response.InvoiceDetailResponse
import edts.base.android.core_data.source.remote.response.InvoiceResponse
import edts.base.android.core_data.source.remote.response.PaymentResponse
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.PaymentData
import edts.base.android.core_domain.repository.IInvoiceRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.Result
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class InvoiceRepository(private val localDataSource: HttpHeaderLocalSource,
                        private val sessionRemoteDataSource: SessionRemoteDataSource,
                        private val invoiceRemoteDataSource: InvoiceRemoteDataSource,
                        private val invoiceLocalDataSource: InvoiceLocalDataSource,
                        private val profileLocalDataSource: ProfileLocalDataSource,
                        private val paymentLocalDataSource: PaymentLocalDataSource
):
    IInvoiceRepository {
    override fun get(isReload: Boolean,
                     status: String,
                     customer: CustomerData?) =
        object : NetworkBoundGetResource<List<InvoiceData>?, List<InvoiceResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<InvoiceResponse>> {
                val cached = profileLocalDataSource.getCached()
                val id = if (customer?.id != null) customer.id else if (cached?.id == null) 0 else cached.id

                invoiceLocalDataSource.key = "${id}_${status}"
                return invoiceRemoteDataSource.get(
                    id = id,
                    status = status
                )
            }

            override fun getCached() = flow {
                val profile = profileLocalDataSource.getCached()
                val id = customer?.id ?: if (profile?.id == null) 0 else profile.id

                val cached = invoiceLocalDataSource.getCached("${id}_${status}")
                emit(
                    Mappers.getMapper(InvoiceMapper::class.java)
                        .invoiceEntityToModel(cached)
                )
            }

            override suspend fun saveCallResult(data: List<InvoiceResponse>) {
                val cached = profileLocalDataSource.getCached()
                val id = if (customer?.id != null) customer.id else if (cached?.id == null) 0 else cached.id

                data.let {
                    val mapper = Mappers.getMapper(InvoiceMapper::class.java)
                        .invoiceResponseToEntity(it)
                    invoiceLocalDataSource.save("${id}_${status}", mapper)
                }
            }

            override fun shouldFetch(data: List<InvoiceData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()

    override fun getDetail(id: Long) =
        object : NetworkBoundProcessResource<InvoiceDetailData?, List<InvoiceDetailResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: List<InvoiceDetailResponse>) =
                Mappers.getMapper(InvoiceMapper::class.java)
                    .invoiceDetailResponseToModel(data[0])

            override suspend fun createCall(): Result<List<InvoiceDetailResponse>> =
                invoiceRemoteDataSource.getDetail(id)


        }.asFlow()

    override fun getPayments(isReload: Boolean, status: String, customer: CustomerData?) =
        object : NetworkBoundGetResource<List<PaymentData>?, List<PaymentResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<PaymentResponse>> {
                val cached = profileLocalDataSource.getCached()
                val id = if (customer?.id != null) customer.id else if (cached?.id == null) 0 else cached.id

                invoiceLocalDataSource.key = "${id}_${status}"
                return invoiceRemoteDataSource.getPayments(
                    id = id,
                    status = status
                )
            }

            override fun getCached() = flow {
                val profile = profileLocalDataSource.getCached()
                val id = customer?.id ?: if (profile?.id == null) 0 else profile.id

                val cached = paymentLocalDataSource.getCached("${id}_${status}")
                emit(
                    Mappers.getMapper(InvoiceMapper::class.java)
                        .paymentEntityToModel(cached)
                )
            }

            override suspend fun saveCallResult(data: List<PaymentResponse>) {
                val cached = profileLocalDataSource.getCached()
                val id = if (customer?.id != null) customer.id else if (cached?.id == null) 0 else cached.id

                data.let {
                    val mapper = Mappers.getMapper(InvoiceMapper::class.java)
                        .paymentResponseToEntity(it)
                    paymentLocalDataSource.save("${id}_${status}", mapper)
                }
            }

            override fun shouldFetch(data: List<PaymentData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()
}
