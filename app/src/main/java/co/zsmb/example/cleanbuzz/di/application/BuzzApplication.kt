package co.zsmb.example.cleanbuzz.di.application

import android.app.Application
import co.zsmb.example.cleanbuzz.di.DaggerApplicationComponent

class BuzzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .retrofitModule(RetrofitModule(baseURL))
                .build()
    }

    companion object {
        private val baseURL = "https://api.buzzcloud.xyz"

        lateinit var applicationComponent: ApplicationComponent
    }

}
