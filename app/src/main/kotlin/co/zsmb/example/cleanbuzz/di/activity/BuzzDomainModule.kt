package co.zsmb.example.cleanbuzz.di.activity

import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import dagger.Module
import dagger.Provides
import rx.Scheduler

@Module
class BuzzDomainModule {

    @Provides @PerActivity
    fun provideBuzzUseCase(buzzRepository: BuzzRepository): BuzzUseCase
            = BuzzUseCase(buzzRepository)

}