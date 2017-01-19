package co.zsmb.example.cleanbuzz.domain

import rx.Observable

interface BuzzRepository {

    fun getBuzz(number: Int): Observable<BuzzResult>

}
