package co.zsmb.example.cleanbuzz.data

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface FizzBuzzAPI {

    @GET("/")
    fun getRxBuzz(@Query("count") count: Int): Observable<List<String>>

}
