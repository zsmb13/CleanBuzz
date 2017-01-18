package co.zsmb.example.cleanbuzz.domain

import rx.Observable
import java.util.*

interface BuzzRepository {

    fun getBuzz(number: Int): Observable<BuzzResult>

}
