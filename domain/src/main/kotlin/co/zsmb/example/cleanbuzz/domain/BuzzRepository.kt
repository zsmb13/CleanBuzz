package co.zsmb.example.cleanbuzz.domain

import io.reactivex.Single

interface BuzzRepository {

    fun getBuzz(number: Int): Single<BuzzResult>

}
