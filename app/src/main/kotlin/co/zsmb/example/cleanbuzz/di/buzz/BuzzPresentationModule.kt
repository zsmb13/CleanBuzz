package co.zsmb.example.cleanbuzz.di.buzz

import android.content.Context
import co.zsmb.example.cleanbuzz.domain.usecase.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.base.PerActivity
import co.zsmb.example.cleanbuzz.presentation.buzz.BuzzPresenter
import co.zsmb.example.cleanbuzz.presentation.buzz.BuzzPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class BuzzPresentationModule {

    @Provides @PerActivity
    fun provideBuzzPresenter(context: Context, buzzUseCase: BuzzUseCase): BuzzPresenter
            = BuzzPresenterImpl(context, buzzUseCase, AndroidSchedulers.mainThread())

}
