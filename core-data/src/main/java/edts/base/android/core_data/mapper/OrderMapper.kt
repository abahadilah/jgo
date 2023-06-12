package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.OrderEntity
import edts.base.android.core_data.source.local.InvoiceEntity
import edts.base.android.core_data.source.local.VehiclePriceEntity
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_data.source.remote.response.VehicleTypeResponse
import edts.base.android.core_data.source.remote.response.OrderResponse
import edts.base.android.core_data.source.remote.response.VehiclePriceResponse
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailLineData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface OrderMapper {

    @Mappings
    fun totalOilResponseToEntity(input: VehiclePriceResponse?): VehiclePriceEntity?

    @Mappings
    fun totalOilListResponseToEntity(input: List<VehiclePriceResponse>?): List<VehiclePriceEntity>?

    @Mappings
    fun orderResponseToEntity(input: List<OrderResponse>?): List<OrderEntity>?

    @Mappings
    fun salesRequestResponseToModel(input: OrderResponse?): OrderData?

    @Mappings
    fun totalOilEntityToModel(input: VehiclePriceEntity?): InvoiceDetailLineData?

    @Mappings
    fun orderEntityToModel(input: List<OrderEntity>?): List<OrderData>?

    @Mappings
    fun orderDetailResponseToModel(input: OrderDetailResponse?): OrderDetailData?

    @Mappings
    fun invoiceResponseToEntity(input: List<VehicleTypeResponse>?): List<InvoiceEntity>?

    @Mappings
    fun invoiceEntityToModel(input: List<InvoiceEntity>?): List<InvoiceData>?

}