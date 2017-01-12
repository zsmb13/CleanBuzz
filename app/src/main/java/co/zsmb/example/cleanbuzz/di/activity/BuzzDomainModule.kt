package co.zsmb.example.cleanbuzz.di.activity

import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
import co.zsmb.example.cleanbuzz._2_domain.BuzzUseCase
import dagger.Module
import dagger.Provides
import rx.Scheduler

@Module
class BuzzDomainModule(
        private val ioScheduler: Scheduler,
        private val uiScheduler: Scheduler) {

    @Provides @PerActivity
    fun provideBuzzUseCase(buzzRepository: BuzzRepository): BuzzUseCase
            = BuzzUseCase(buzzRepository, ioScheduler, uiScheduler)

}
