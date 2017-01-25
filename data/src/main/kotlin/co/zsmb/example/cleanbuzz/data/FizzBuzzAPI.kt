package co.zsmb.example.cleanbuzz.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FizzBuzzAPI {

    @GET("/")
    fun getRxBuzz(@Query("count") count: Int): Single<List<String>>

}
