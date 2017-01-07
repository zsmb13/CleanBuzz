package co.zsmb.example.cleanbuzz._2_domain

import co.zsmb.example.cleanbuzz._2_domain.base.UseCase
import rx.Observable
import javax.inject.Inject

class BuzzUseCase @Inject constructor(val buzzRepository: BuzzRepository)
    : UseCase<BuzzResult>() {

    var number: Int = 0

    override fun createObservable(): Observable<BuzzResult>
            = buzzRepository.getBuzz(number)

}
