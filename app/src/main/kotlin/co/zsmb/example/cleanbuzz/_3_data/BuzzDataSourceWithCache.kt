package co.zsmb.example.cleanbuzz._3_data

interface BuzzDataSourceWithCache : BuzzDataSource {

    fun cacheResults(results: List<String>)

}