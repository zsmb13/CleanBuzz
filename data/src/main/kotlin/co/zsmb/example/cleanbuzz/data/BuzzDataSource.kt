package co.zsmb.example.cleanbuzz.data

import io.reactivex.Observable

interface BuzzDataSource {

    fun getBuzz(number: Int): Observable<List<String>>

}
