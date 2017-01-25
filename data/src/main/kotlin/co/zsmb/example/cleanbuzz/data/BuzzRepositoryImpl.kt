package co.zsmb.example.cleanbuzz.data

import co.zsmb.example.cleanbuzz.domain.BuzzResult
import io.reactivex.Single
import javax.inject.Inject

class BuzzRepositoryImpl
@Inject constructor(
        private val memoryDataSource: BuzzDataSourceWithCache,
        private val networkDataSource: BuzzDataSource)
    : co.zsmb.example.cleanbuzz.domain.BuzzRepository {

    override fun getBuzz(number: Int): Single<BuzzResult>
            = memory(number)
            .onErrorResumeNext { networkWithCache(number) }
            .map { BuzzResult(it.last()) }

    private fun memory(number: Int) = memoryDataSource.getBuzz(number)

    private fun networkWithCache(number: Int) =
            networkDataSource
                    .getBuzz(number)
                    .doAfterSuccess { memoryDataSource.cacheResults(it) }

}
