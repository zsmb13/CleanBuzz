package co.zsmb.example.cleanbuzz.data

import io.reactivex.Observable
import javax.inject.Inject

class BuzzRepositoryImpl
@Inject constructor(
        private val memoryDataSource: BuzzDataSourceWithCache,
        private val networkDataSource: BuzzDataSource)
    : co.zsmb.example.cleanbuzz.domain.BuzzRepository {

    override fun getBuzz(number: Int): Observable<co.zsmb.example.cleanbuzz.domain.BuzzResult>
            = memory(number)
            .switchIfEmpty(networkWithCache(number))
            .map { co.zsmb.example.cleanbuzz.domain.BuzzResult(it.last()) }

    private fun memory(number: Int) = memoryDataSource.getBuzz(number)

    private fun networkWithCache(number: Int) = Observable.defer {
        networkDataSource.getBuzz(number)
                .doOnNext {
                    memoryDataSource.cacheResults(it)
                }
    }

}
