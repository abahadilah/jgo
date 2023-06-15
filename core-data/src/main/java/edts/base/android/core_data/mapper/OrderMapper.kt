package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.CreateOrderAddressEntity
import edts.base.android.core_data.source.local.OrderEntity
import edts.base.android.core_data.source.local.InvoiceEntity
import edts.base.android.core_data.source.remote.response.CreateOrderResponse
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_data.source.remote.response.VehicleTypeResponse
import edts.base.android.core_data.source.remote.response.OrderResponse
import edts.base.android.core_domain.model.CreateOrderAddressData
import edts.base.android.core_domain.model.CreateOrderData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_domain.model.InvoiceData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface OrderMapper {
    @Mappings
    fun orderResponseToEntity(input: List<OrderResponse>?): List<OrderEntity>?

    @Mappings
    fun orderEntityToModel(input: List<OrderEntity>?): List<OrderData>?

    @Mappings
    fun orderDetailResponseToModel(input: OrderDetailResponse?): OrderDetailData?

    @Mappings
    fun invoiceResponseToEntity(input: List<VehicleTypeResponse>?): List<InvoiceEntity>?

    @Mappings
    fun invoiceEntityToModel(input: List<InvoiceEntity>?): List<InvoiceData>?

    @Mappings
    fun createOrderResponseToModel(input: CreateOrderResponse?): CreateOrderData?

    @Mappings
    fun createOrderAddressEntityToRequest(input: List<CreateOrderAddressEntity>?): List<CreateOrderAddressData>?
    
}