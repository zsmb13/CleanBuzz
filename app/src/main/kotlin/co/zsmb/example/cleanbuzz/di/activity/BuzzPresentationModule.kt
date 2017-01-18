package co.zsmb.example.cleanbuzz.di.activity

import android.content.Context
import co.zsmb.example.cleanbuzz.presentation.BuzzPresenter
import co.zsmb.example.cleanbuzz.presentation.BuzzPresenterImpl
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import dagger.Module
import dagger.Provides
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Module
class BuzzPresentationModule {

    @Provides @PerActivity
    fun provideBuzzPresenter(context: Context, buzzUseCase: BuzzUseCase): BuzzPresenter
            = BuzzPresenterImpl(context, buzzUseCase, Schedulers.io(), AndroidSchedulers.mainThread())

}