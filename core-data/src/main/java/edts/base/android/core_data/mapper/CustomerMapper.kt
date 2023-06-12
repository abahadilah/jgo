package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.ProfileEntity
import edts.base.android.core_data.source.remote.response.CheckPriceResponse
import edts.base.android.core_data.source.remote.response.ProfileResponse
import edts.base.android.core_domain.model.CheckPhoneData
import edts.base.android.core_domain.model.ProfileData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface CustomerMapper {

    @Mappings
    fun checkPhoneResponseToModel(input: CheckPriceResponse?): CheckPhoneData?

    @Mappings
    fun profileResponseToEntity(input: ProfileResponse?): ProfileEntity?

    @Mappings
    fun profileEntityToModel(input: ProfileEntity?): ProfileData?

}