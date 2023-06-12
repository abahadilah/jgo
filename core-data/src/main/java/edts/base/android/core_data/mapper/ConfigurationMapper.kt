package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.ConfigurationEntity
import edts.base.android.core_domain.model.ConfigurationData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface ConfigurationMapper {

    @Mappings
    fun configurationEntityToModel(input: ConfigurationEntity?): ConfigurationData?


}