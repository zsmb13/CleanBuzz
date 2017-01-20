package co.zsmb.example.cleanbuzz.domain

import io.reactivex.Observable

interface BuzzRepository {

    fun getBuzz(number: Int): Observable<BuzzResult>

}
