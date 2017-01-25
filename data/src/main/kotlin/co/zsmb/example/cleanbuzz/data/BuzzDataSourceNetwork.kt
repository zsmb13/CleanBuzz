package co.zsmb.example.cleanbuzz.data

import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class BuzzDataSourceNetwork @Inject constructor(retrofit: Retrofit) : BuzzDataSource {

    private val service: FizzBuzzAPI = retrofit.create(FizzBuzzAPI::class.java)

    override fun getBuzz(number: Int): Single<List<String>> {
        return service.getRxBuzz(number)
    }

}
