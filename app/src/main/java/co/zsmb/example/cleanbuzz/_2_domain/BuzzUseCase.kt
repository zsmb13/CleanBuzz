package co.zsmb.example.cleanbuzz._2_domain

import co.zsmb.example.cleanbuzz._2_domain.base.UseCase
import rx.Observable
import javax.inject.Inject

class BuzzUseCase @Inject constructor(
        private val buzzRepository: BuzzRepository)
    : UseCase<BuzzResult>() {

    // TODO provide this parameter in a nicer way somehow
    var number: Int = 0

    override fun createObservable(): Observable<BuzzResult>
            = buzzRepository.getBuzz(number)

}
