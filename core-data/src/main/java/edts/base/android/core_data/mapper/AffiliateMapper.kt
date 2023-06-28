package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.CustomerEntity
import edts.base.android.core_data.source.local.IdNameEntity
import edts.base.android.core_data.source.remote.response.CustomerResponse
import edts.base.android.core_data.source.remote.response.IdNameResponse
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.IdNameData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface AffiliateMapper {
    @Mappings
    fun customerEntityToModel(input: List<CustomerEntity>?): List<CustomerData>?

    @Mappings
    fun customerResponseToEntity(input: List<CustomerResponse>?): List<CustomerEntity>?

    @Mappings
    fun provincesResponseToEntity(input: List<IdNameResponse>?): List<IdNameEntity>?

    @Mappings
    fun provincesEntityToModel(input: List<IdNameEntity>?): List<IdNameData>?

}