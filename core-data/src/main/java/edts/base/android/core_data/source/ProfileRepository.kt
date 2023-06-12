package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.CustomerMapper
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.remote.CustomerRemoteDataSource
import edts.base.android.core_data.source.remote.response.ProfileResponse
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_domain.repository.IProfileRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import id.co.edtslib.data.source.remote.response.ApiResponse
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class ProfileRepository(private val profileLocalDataSource: ProfileLocalDataSource):
    IProfileRepository {
    override fun getProfile() = flow {
        val cached = profileLocalDataSource.getCached()
        val data = Mappers.getMapper(CustomerMapper::class.java)
            .profileEntityToModel(cached)
        emit(data)

    }
}
