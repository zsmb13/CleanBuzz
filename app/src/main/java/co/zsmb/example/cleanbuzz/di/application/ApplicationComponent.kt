package co.zsmb.example.cleanbuzz.di.application

import android.content.Context
import co.zsmb.example.cleanbuzz._2_domain.BuzzRepository
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

    //fun inject(buzzActivity: BuzzActivity)

    fun context(): Context

    fun buzzRepository(): BuzzRepository

}
