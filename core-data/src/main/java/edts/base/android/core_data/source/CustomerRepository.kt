package edts.base.android.core_data.source

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import edts.base.android.core_data.mapper.CustomerMapper
import edts.base.android.core_data.source.local.*
import edts.base.android.core_data.source.remote.*
import edts.base.android.core_data.source.remote.response.*
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_domain.repository.ICustomerRepository
import edts.base.core_utils.getDeviceName
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class CustomerRepository(
    private val localDataSource: HttpHeaderLocalSource,
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val customerRemoteDataSource: CustomerRemoteDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val orderLocalDataSource: OrderLocalDataSource,
    private val invoiceLocalDataSource: InvoiceLocalDataSource,
    private val paymentLocalDataSource: PaymentLocalDataSource,
    private val context: Context,
) :
    ICustomerRepository {

    override fun login(username: String, password: String) =
        object : NetworkBoundProcessResource<ProfileData?, List<ProfileResponse?>>(
            localDataSource, sessionRemoteDataSource
        ) {
            @SuppressLint("HardwareIds")
            override suspend fun callBackResult(data: List<ProfileResponse?>): ProfileData? {
                return if (data.isNotEmpty()) {
                    val entity = Mappers.getMapper(CustomerMapper::class.java)
                        .profileResponseToEntity(data[0])
                    profileLocalDataSource.save(entity)

                    Mappers.getMapper(CustomerMapper::class.java)
                        .profileEntityToModel(profileLocalDataSource.getCached())
                }
                else {
                    null
                }
            }


            @SuppressLint("HardwareIds")
            override suspend fun createCall() = customerRemoteDataSource.login(
                username = username,
                password = password,
                deviceId = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                ),
                deviceType ="ANDROID", deviceOsName = getDeviceName()
            )
        }.asFlow()

    override fun logout() = flow {
        profileLocalDataSource.clear()
        orderLocalDataSource.clear()
        invoiceLocalDataSource.clear()
        paymentLocalDataSource.clear()
        emit(true)
    }
}