package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.InvoiceEntity
import edts.base.android.core_data.source.remote.response.InvoiceDetailResponse
import edts.base.android.core_data.source.remote.response.InvoiceResponse
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.OrderDetailData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface InvoiceMapper {

    @Mappings
    fun invoiceResponseToEntity(input: List<InvoiceResponse>?): List<InvoiceEntity>?

    @Mappings
    fun invoiceEntityToModel(input: List<InvoiceEntity>?): List<InvoiceData>?

    @Mappings
    fun invoiceDetailResponseToModel(input: InvoiceDetailResponse?): InvoiceDetailData?
}