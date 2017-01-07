package co.zsmb.example.cleanbuzz._1_presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.zsmb.example.cleanbuzz.di.DaggerActivityComponent
import co.zsmb.example.cleanbuzz.di.activity.ActivityComponent
import co.zsmb.example.cleanbuzz.di.application.BuzzApplication

abstract class BaseView<P : LifecycleObserver> : AppCompatActivity() {

    lateinit var presenter: P

    lateinit var activityComponent: ActivityComponent

    abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()
        initPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isChangingConfigurations) {
            presenter.onTerminate()
        }
    }

    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .buzzComponent(BuzzApplication.applicationComponent)
                .build()
    }

    private fun initPresenter() {
        val oldPresenter = lastCustomNonConfigurationInstance
        if (oldPresenter == null) {
            presenter = createPresenter()
            presenter.onInit()
        }
        else {
            @Suppress("UNCHECKED_CAST")
            presenter = oldPresenter as P
        }
    }

    final override fun onRetainCustomNonConfigurationInstance() = presenter

}
