package co.zsmb.example.cleanbuzz.domain

import co.zsmb.example.cleanbuzz.domain.base.UseCase
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

class BuzzUseCase @Inject constructor(private val buzzRepository: BuzzRepository)
    : UseCase<BuzzResult, Int>() {

    override fun createObservable(params: Int) = buzzRepository.getBuzz(params)

}
