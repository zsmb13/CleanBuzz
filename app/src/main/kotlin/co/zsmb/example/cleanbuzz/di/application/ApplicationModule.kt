package co.zsmb.example.cleanbuzz.di.application

import android.content.Context
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides @Singleton
    fun provideContext(): Context {
        return context
    }

}
