package co.zsmb.example.cleanbuzz.di.activity

import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
import co.zsmb.example.cleanbuzz._2_domain.BuzzUseCase
import dagger.Module
import dagger.Provides

@Module
class BuzzDomainModule {

    @Provides @PerActivity
    fun provideBuzzUseCase(buzzRepository: BuzzRepository): BuzzUseCase
            = BuzzUseCase(buzzRepository)

}
