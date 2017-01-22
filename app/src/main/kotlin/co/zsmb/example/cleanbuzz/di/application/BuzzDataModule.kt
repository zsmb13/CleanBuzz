package co.zsmb.example.cleanbuzz.di.application

import co.zsmb.example.cleanbuzz.data.BuzzDataSourceMemory
import co.zsmb.example.cleanbuzz.data.BuzzDataSourceNetwork
import co.zsmb.example.cleanbuzz.data.BuzzRepositoryImpl
import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BuzzDataModule {

    @Provides @Singleton
    fun provideBuzzRepository(memoryDataSource: BuzzDataSourceMemory,
                              networkDataSource: BuzzDataSourceNetwork): BuzzRepository
            = BuzzRepositoryImpl(memoryDataSource, networkDataSource)

    @Provides @Singleton
    fun provideBuzzDataSourceNetwork(retrofit: Retrofit) = BuzzDataSourceNetwork(retrofit)

    @Provides @Singleton
    fun provideBuzzDataSourceMemory() = BuzzDataSourceMemory()

}
