package co.zsmb.example.cleanbuzz.di.application

import android.content.Context
import co.zsmb.example.cleanbuzz.presentation.base.Navigator
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides @Singleton
    fun provideContext(): Context = context

    @Provides @Singleton
    fun provideNavigator(context: Context): Navigator = Navigator(context)

}
