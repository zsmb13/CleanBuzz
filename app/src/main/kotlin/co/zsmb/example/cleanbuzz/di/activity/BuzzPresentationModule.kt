package co.zsmb.example.cleanbuzz.di.activity

import android.content.Context
import co.zsmb.example.cleanbuzz._1_presentation.BuzzPresenter
import co.zsmb.example.cleanbuzz._1_presentation.BuzzPresenterImpl
import co.zsmb.example.cleanbuzz._2_domain.BuzzUseCase
import dagger.Module
import dagger.Provides

@Module
class BuzzPresentationModule {

    @Provides @PerActivity
    fun provideBuzzPresenter(context: Context, buzzUseCase: BuzzUseCase): BuzzPresenter
            = BuzzPresenterImpl(context, buzzUseCase)

}
