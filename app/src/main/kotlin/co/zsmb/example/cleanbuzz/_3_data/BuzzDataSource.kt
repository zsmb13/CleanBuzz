package co.zsmb.example.cleanbuzz._3_data

import rx.Observable

interface BuzzDataSource {

    fun getBuzz(number: Int): Observable<List<String>>

}
