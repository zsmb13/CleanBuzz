package co.zsmb.example.cleanbuzz.data

import io.reactivex.Single

interface BuzzDataSource {

    fun getBuzz(number: Int): Single<List<String>>

}
