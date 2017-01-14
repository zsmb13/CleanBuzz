package co.zsmb.example.cleanbuzz._3_data

import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
import co.zsmb.example.cleanbuzz._2_domain.BuzzResult
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable

@RunWith(MockitoJUnitRunner::class)
class BuzzRepositoryImplTest {

    lateinit var buzzRepository: BuzzRepository

    @Mock lateinit var memoryDataSource: BuzzDataSourceMemory
    @Mock lateinit var networkDataSource: BuzzDataSourceNetwork

    companion object {
        private val NETWORK_RESULT = "NETWORK_RESULT"
        private val MEMORY_RESULT = "MEMORY_RESULT"
    }

    @Before
    fun setUp() {
        buzzRepository = BuzzRepositoryImpl(memoryDataSource, networkDataSource)
    }

    @Test
    fun getBuzz_usesMemoryFirst() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Observable.just(listOf(MEMORY_RESULT))

        val observable = buzzRepository.getBuzz(5)

        assertEquals(MEMORY_RESULT, observable.getData())
    }

    @Test
    fun getBuzz_doesNotUseNetworkIfResultIsInMemory() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Observable.just(listOf(MEMORY_RESULT))

        buzzRepository.getBuzz(5)

        verify(networkDataSource, never()).getBuzz(any())
    }

    @Test
    fun getBuzz_usesNetworkIfResultIsNotInMemory() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Observable.empty<List<String>>()
        whenever(networkDataSource.getBuzz(any())) doReturn Observable.just(listOf(NETWORK_RESULT))

        val observable = buzzRepository.getBuzz(5)

        assertEquals(NETWORK_RESULT, observable.getData())
    }

    @Test
    fun getBuzz_cachesNetworkResultsInMemory() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Observable.empty<List<String>>()
        whenever(networkDataSource.getBuzz(any())) doReturn Observable.just(listOf(NETWORK_RESULT))

        val observable = buzzRepository.getBuzz(5)
        observable.getData()

        verify(memoryDataSource).cacheResults(listOf(NETWORK_RESULT))
    }

    fun Observable<BuzzResult>.getData(): String = toBlocking().first().result

}