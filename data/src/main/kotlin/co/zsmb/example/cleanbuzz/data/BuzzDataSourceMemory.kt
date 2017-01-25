package co.zsmb.example.cleanbuzz.data

import io.reactivex.Single

class BuzzDataSourceMemory : BuzzDataSourceWithCache {

    private val buzzResults = mutableListOf("0") // leading 0 for indexes to line up nicely

    override fun cacheResults(results: List<String>) {
        if (results.size < buzzResults.size) {
            return
        }

        val startIndex = buzzResults.size - 1
        val subList = results.subList(startIndex)
        buzzResults.addAll(subList)
    }

    override fun getBuzz(number: Int): Single<List<String>> =
            if (number > 0 && number < buzzResults.size) {
                val result = buzzResults[number]
                Single.just(listOf(result))
            }
            else {
                Single.error<List<String>>(Exception("No items"))
            }

    fun <E> List<E>.subList(fromIndex: Int): List<E> {
        return this.subList(fromIndex, this.lastIndex + 1)
    }

}
