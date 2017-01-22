package co.zsmb.example.cleanbuzz.domain.usecase

import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import co.zsmb.example.cleanbuzz.domain.BuzzResult
import co.zsmb.example.cleanbuzz.domain.base.UseCase
import io.reactivex.Scheduler
import javax.inject.Inject

class BuzzUseCase @Inject constructor(
        private val buzzRepository: BuzzRepository,
        scheduler: Scheduler)
    : UseCase<BuzzResult, Int>(scheduler) {

    override fun createObservable(params: Int) = buzzRepository.getBuzz(params)

}
