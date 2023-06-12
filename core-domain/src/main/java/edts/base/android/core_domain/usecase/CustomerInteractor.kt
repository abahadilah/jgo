package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.ICustomerRepository

class CustomerInteractor(private val repository: ICustomerRepository): CustomerUseCase {
    override fun login(username: String, password: String) = repository.login(username, password)
    override fun logout() = repository.logout()

}