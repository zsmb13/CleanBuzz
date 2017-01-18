package co.zsmb.example.cleanbuzz.data

interface BuzzDataSourceWithCache : BuzzDataSource {

    fun cacheResults(results: List<String>)

}