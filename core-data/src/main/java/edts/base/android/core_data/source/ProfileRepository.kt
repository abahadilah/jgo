package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.CustomerMapper
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_domain.repository.IProfileRepository
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class ProfileRepository(private val profileLocalDataSource: ProfileLocalDataSource):
    IProfileRepository {
    override fun get() = flow {
        val cached = profileLocalDataSource.getCached()
        val data = Mappers.getMapper(CustomerMapper::class.java)
            .profileEntityToModel(cached)
        emit(data)
    }
}
