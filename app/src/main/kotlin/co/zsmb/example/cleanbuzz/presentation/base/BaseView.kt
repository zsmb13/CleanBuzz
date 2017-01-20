package co.zsmb.example.cleanbuzz.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseView<P : LifecycleObserver, C : Any> : AppCompatActivity() {

    protected lateinit var presenter: P

    protected lateinit var activityComponent: C

    protected abstract fun createPresenter(): P

    protected abstract fun createComponent(): C

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
        if (!isChangingConfigurations) {
            presenter.onTerminate()
        }
    }

    private fun initActivityComponent() {
        val oldComponent = lastCustomNonConfigurationInstance

        if (oldComponent == null) {
            activityComponent = createComponent()
        }
        else {
            // The object stored can only be put there by our own
            // onRetainCustomNonConfigurationInstance method, and
            // is always of type C or is null
            @Suppress("UNCHECKED_CAST")
            activityComponent = oldComponent as C
        }
    }

    private fun initPresenter() {
        presenter = createPresenter()
    }

    final override fun onRetainCustomNonConfigurationInstance() = activityComponent

}
