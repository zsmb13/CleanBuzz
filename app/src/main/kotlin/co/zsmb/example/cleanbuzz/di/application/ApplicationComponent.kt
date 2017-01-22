package co.zsmb.example.cleanbuzz.di.application

import android.content.Context
import co.zsmb.example.cleanbuzz.domain.BuzzRepository
import co.zsmb.example.cleanbuzz.presentation.base.Navigator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                ApplicationModule::class,
                BuzzDataModule::class,
                RetrofitModule::class
        ))
interface ApplicationComponent {

    fun context(): Context

    fun navigator(): Navigator

    fun buzzRepository(): BuzzRepository

}
