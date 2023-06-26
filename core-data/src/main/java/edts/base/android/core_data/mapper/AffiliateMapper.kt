package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.CustomerEntity
import edts.base.android.core_data.source.remote.response.CustomerResponse
import edts.base.android.core_domain.model.CustomerData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface AffiliateMapper {
    @Mappings
    fun customerEntityToModel(input: List<CustomerEntity>?): List<CustomerData>?

    @Mappings
    fun customerResponseToEntity(input: List<CustomerResponse>?): List<CustomerEntity>?

}