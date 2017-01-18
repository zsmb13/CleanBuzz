package co.zsmb.example.cleanbuzz.data

import rx.Observable

interface BuzzDataSource {

    fun getBuzz(number: Int): Observable<List<String>>

}
