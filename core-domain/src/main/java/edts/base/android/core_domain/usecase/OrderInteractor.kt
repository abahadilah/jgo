package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IOrderRepository

class OrderInteractor(private val repository: IOrderRepository): OrderUseCase {
    override fun get(isReload: Boolean, status: String) = repository.get(isReload, status)
    override fun getDetail(orderId: Long) = repository.getDetail(orderId)

    override fun createOrder(vehicleType: Long, product: String,
                             length: Int?, width: Int?, height: Int?, coli: Int?, weight: Int?,
                             description: String?, originName: String, originCity: String?,
                             originLat: Double, originLng: Double, destinationName: String,
                             destinationCity: String?, destinationLat: Double, destinationLng: Double,
                             destinationName2: String?,
                             destinationCity2: String?,
                             destinationLat2: Double?,
                             destinationLng2: Double?,
                             destinationName3: String?,
                             destinationCity3: String?,
                             destinationLat3: Double?,
                             destinationLng3: Double?,
                             destinationName4: String?,
                             destinationCity4: String?,
                             destinationLat4: Double?,
                             destinationLng4: Double?,
                             destinationName5: String?,
                             destinationCity5: String?,
                             destinationLat5: Double?,
                             destinationLng5: Double?) =
        repository.createOrder(vehicleType, product, length, width, height, coli, weight,
            description, originName, originCity, originLat, originLng, destinationName,
            destinationCity, destinationLat, destinationLng,
            destinationName2, destinationCity2, destinationLat2, destinationLng2,
            destinationName3, destinationCity3, destinationLat3, destinationLng3,
            destinationName4, destinationCity4, destinationLat4, destinationLng4,
            destinationName5, destinationCity5, destinationLat5, destinationLng5)

    override fun getOriginAddressHistory() = repository.getOriginAddressHistory()
    override fun getDestinationAddressHistory() = repository.getDestinationAddressHistory()
}