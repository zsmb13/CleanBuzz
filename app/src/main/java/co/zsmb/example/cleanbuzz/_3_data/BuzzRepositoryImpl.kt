package co.zsmb.example.cleanbuzz._3_data

import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
import co.zsmb.example.cleanbuzz._2_domain.BuzzResult
import rx.Observable
import javax.inject.Inject

class BuzzRepositoryImpl
@Inject constructor(
        private val memoryDataSource: BuzzDataSourceMemory,
        private val networkDataSource: BuzzDataSourceNetwork)
    : BuzzRepository {

    override fun getBuzz(number: Int): Observable<BuzzResult>
            = memory(number)
            .switchIfEmpty(networkWithCache(number))
            .map { BuzzResult(it.last()) }

    private fun memory(number: Int) = memoryDataSource.getBuzz(number)

    private fun networkWithCache(number: Int) = Observable.defer {
        networkDataSource.getBuzz(number)
                .doOnNext {
                    memoryDataSource.cacheResults(it)
                }
    }


}
