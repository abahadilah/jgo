package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.remote.response.LocationResponse
import edts.base.android.core_domain.model.VehicleTypeData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface LocationMapper {

    @Mappings
    fun locationListResponseToModel(input: List<LocationResponse>?): List<VehicleTypeData>?

    @Mappings
    fun locationResponseToModel(input: LocationResponse?): VehicleTypeData?

}