package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.OtpApiService
import edts.base.android.core_data.source.remote.request.OtpVerifyRequest
import id.co.edtslib.data.BaseDataSource

class OtpRemoteDataSource(
    private val otpApiService: OtpApiService
) : BaseDataSource() {

    suspend fun requestRegistration(phoneNo: String) =
        getResult { otpApiService.requestRegistrationOtp(phoneNo) }

    suspend fun resendRegistration(phoneNo: String) =
        getResult { otpApiService.resendRegistrationOtp(phoneNo) }

    suspend fun verifyRegistration(phoneNo: String, otp: String) =
        getResult { otpApiService.verifyRegistrationOtp(
            OtpVerifyRequest(
                phoneNumber = phoneNo,
                otp = otp)) }

}