package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.VehicleTypeEntity
import edts.base.android.core_data.source.remote.response.CheckPriceResponse
import edts.base.android.core_data.source.remote.response.VehicleTypeResponse
import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.VehicleTypeData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface VehicleMapper {

    @Mappings
    fun vehicleTypeResponseToEntity(input: List<VehicleTypeResponse>?): List<VehicleTypeEntity>?

    @Mappings
    fun vehicleTypeEntityToModel(input: List<VehicleTypeEntity>?): List<VehicleTypeData>?

    @Mappings
    fun vehiclePriceResponseToModel(input: CheckPriceResponse): CheckPriceData?

}