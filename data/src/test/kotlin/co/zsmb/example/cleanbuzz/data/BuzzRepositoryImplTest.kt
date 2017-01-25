package co.zsmb.example.cleanbuzz.data

import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import co.zsmb.example.cleanbuzz.domain.BuzzResult
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BuzzRepositoryImplTest {

    lateinit var buzzRepository: BuzzRepository

    @Mock lateinit var memoryDataSource: BuzzDataSourceWithCache
    @Mock lateinit var networkDataSource: BuzzDataSource

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
        whenever(memoryDataSource.getBuzz(any())) doReturn Single.just(listOf(MEMORY_RESULT))

        val result = buzzRepository.getBuzz(5)

        assertEquals(MEMORY_RESULT, result.getData())
    }

    @Test
    fun getBuzzWhenMemoryHasResult_doesNotUseNetwork() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Single.just(listOf(MEMORY_RESULT))

        buzzRepository.getBuzz(5)

        verify(networkDataSource, never()).getBuzz(any())
    }

    @Test
    fun getBuzzWhenMemoryDoesNotHaveResult_usesNetwork() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Single.error<List<String>>(Exception(""))
        whenever(networkDataSource.getBuzz(any())) doReturn Single.just(listOf(NETWORK_RESULT))

        val result = buzzRepository.getBuzz(5)

        assertEquals(NETWORK_RESULT, result.getData())
    }

    @Test
    fun getBuzz_cachesNetworkResultsInMemory() {
        whenever(memoryDataSource.getBuzz(any())) doReturn Single.error<List<String>>(Exception(""))
        whenever(networkDataSource.getBuzz(any())) doReturn Single.just(listOf(NETWORK_RESULT))

        val observable = buzzRepository.getBuzz(5)
        observable.getData()

        verify(memoryDataSource).cacheResults(listOf(NETWORK_RESULT))
    }

    fun Single<BuzzResult>.getData(): String = blockingGet().result

}