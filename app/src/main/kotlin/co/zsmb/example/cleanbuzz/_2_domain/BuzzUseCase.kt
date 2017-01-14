package co.zsmb.example.cleanbuzz._2_domain

import co.zsmb.example.cleanbuzz._2_domain.base.UseCase
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

class BuzzUseCase @Inject constructor(private val buzzRepository: BuzzRepository)
    : UseCase<BuzzResult, Int>() {

    override fun createObservable(params: Int) = buzzRepository.getBuzz(params)

}
