package co.zsmb.example.cleanbuzz.data

import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BuzzDataSourceMemoryTest {

    lateinit var buzzDataSourceMemory: BuzzDataSourceMemory

    @Before
    fun setUp() {
        buzzDataSourceMemory = BuzzDataSourceMemory()
        buzzDataSourceMemory.cacheResults(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
    }

    @Test
    fun requestWithBelowMinValue_returnsEmpty() {
        val result = buzzDataSourceMemory.getBuzz(0)

        assert(result.isEmpty())
    }

    @Test
    fun requestWithAboveMaxValue_returnsEmpty() {
        val result = buzzDataSourceMemory.getBuzz(1000)

        assert(result.isEmpty())
    }

    @Test
    fun requestWithNegativeValue_returnsEmpty() {
        val result = buzzDataSourceMemory.getBuzz(-10)

        assert(result.isEmpty())
    }

    @Test
    fun requestForNonCachedData1_returnsEmpty() {
        val result = buzzDataSourceMemory.getBuzz(11)

        assert(result.isEmpty())
    }

    @Test
    fun requestForNonCachedData2_returnsEmpty() {
        val result = buzzDataSourceMemory.getBuzz(200)

        assert(result.isEmpty())
    }

    @Test
    fun requestForCachedData1_returnsCorrectData() {
        val result = buzzDataSourceMemory.getBuzz(5)

        assertEquals("5", result.getData())
    }

    @Test
    fun requestForCachedData2_returnsCorrectData() {
        val result = buzzDataSourceMemory.getBuzz(10)

        assertEquals("10", result.getData())
    }

    fun Single<List<String>>.isEmpty(): Boolean {
        try {
            blockingGet()
            return false
        } catch (e: Exception) {
            return true
        }
    }

    fun Single<List<String>>.getData(): String = blockingGet().first()

}
