package co.zsmb.example.cleanbuzz.di.buzz

import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import co.zsmb.example.cleanbuzz.domain.usecase.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.base.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers

@Module
class BuzzDomainModule {

    @Provides @PerActivity
    fun provideBuzzUseCase(buzzRepository: BuzzRepository): BuzzUseCase
            = BuzzUseCase(buzzRepository, Schedulers.io())

}
