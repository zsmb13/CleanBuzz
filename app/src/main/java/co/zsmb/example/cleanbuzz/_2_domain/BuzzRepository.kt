package co.zsmb.example.cleanbuzz._2_domain

import rx.Observable

interface BuzzRepository {

    fun getBuzz(number: Int): Observable<BuzzResult>

}
