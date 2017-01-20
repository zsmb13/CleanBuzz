package co.zsmb.example.cleanbuzz.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FizzBuzzAPI {

    @GET("/")
    fun getRxBuzz(@Query("count") count: Int): Observable<List<String>>

}
