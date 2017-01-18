package co.zsmb.example.cleanbuzz.data

import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject

class BuzzDataSourceNetwork @Inject constructor(retrofit: Retrofit) : BuzzDataSource {

    private val service: FizzBuzzAPI = retrofit.create(FizzBuzzAPI::class.java)

    override fun getBuzz(number: Int): Observable<List<String>> {
        return service.getRxBuzz(number)
    }

}
