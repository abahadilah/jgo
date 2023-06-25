package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IProfileRepository

class ProfileInteractor(private val repository: IProfileRepository): ProfileUseCase {
    override fun get() = repository.get()
}