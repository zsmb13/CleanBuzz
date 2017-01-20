package co.zsmb.example.cleanbuzz.presentation.base

abstract class BasePresenter<V : Any>
    : Presenter<V> {

    protected var view: V? = null

    abstract fun restoreViewState()

    override fun bind(view: V) {
        this.view = view
        restoreViewState()
    }

    override fun unbind() {
        this.view = null
    }

    override fun onTerminate() {
        unbind()
    }

    override fun onStart() {}
    override fun onPause() {}
    override fun onResume() {}
    override fun onStop() {}

}
