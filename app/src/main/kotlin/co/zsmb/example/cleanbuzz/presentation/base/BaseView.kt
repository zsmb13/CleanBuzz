package co.zsmb.example.cleanbuzz.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

abstract class BaseView<PR : Terminable, out AC : ActivityComponent<PR>> : AppCompatActivity(), View {

    private lateinit var activityComponent: AC

    protected lateinit var presenter: PR

    protected abstract fun createComponent(): AC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()
        initPresenter()
    }

    private fun initActivityComponent() {
        val oldComponent = lastCustomNonConfigurationInstance

        if (oldComponent == null) {
            activityComponent = createComponent()
        }
        else {
            // The object stored can only be put there by our own
            // onRetainCustomNonConfigurationInstance method, and
            // is always of type AC or is null
            @Suppress("UNCHECKED_CAST")
            activityComponent = oldComponent as AC
        }
    }

    final override fun onRetainCustomNonConfigurationInstance() = activityComponent

    private fun initPresenter() {
        presenter = activityComponent.createPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            presenter.onTerminate()
        }
    }

    override fun close() = finish()

    override fun showMessage(message: String) = toast(message)

    override fun showError(error: String) = toast(error)

}
