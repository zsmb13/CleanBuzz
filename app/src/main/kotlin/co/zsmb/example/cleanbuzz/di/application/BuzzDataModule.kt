package co.zsmb.example.cleanbuzz.di.application

import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BuzzDataModule {

    @Provides @Singleton
    fun provideBuzzRepository(memoryDataSource: co.zsmb.example.cleanbuzz.data.BuzzDataSourceMemory,
                              networkDataSource: co.zsmb.example.cleanbuzz.data.BuzzDataSourceNetwork): BuzzRepository
            = co.zsmb.example.cleanbuzz.data.BuzzRepositoryImpl(memoryDataSource, networkDataSource)

    @Provides @Singleton
    fun provideBuzzDataSourceNetwork(retrofit: Retrofit) = co.zsmb.example.cleanbuzz.data.BuzzDataSourceNetwork(retrofit)

    @Provides @Singleton
    fun provideBuzzDataSourceMemory() = co.zsmb.example.cleanbuzz.data.BuzzDataSourceMemory()

}
