package co.zsmb.example.cleanbuzz.data

import rx.Observable

class BuzzDataSourceMemory : BuzzDataSourceWithCache {

    val buzzResults = mutableListOf("0") // leading 0 for indexes to line up nicely

    override fun cacheResults(results: List<String>) {
        if(results.size < buzzResults.size) {
            return
        }

        val startIndex = buzzResults.size - 1
        val subList = results.subList(startIndex)
        buzzResults.addAll(subList)
    }

    override fun getBuzz(number: Int): Observable<List<String>> =
            if (number > 0 && number < buzzResults.size) {
                val result = buzzResults[number]
                Observable.just(listOf(result))
            }
            else {
                Observable.empty<List<String>>()
            }

    fun <E> List<E>.subList(fromIndex: Int): List<E> {
        return this.subList(fromIndex, this.lastIndex + 1)
    }

}
