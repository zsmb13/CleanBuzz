package co.zsmb.example.cleanbuzz.presentation.base

import co.zsmb.example.cleanbuzz.domain.base.Cancellable

abstract class BasePresenter<V : Any>(val useCases: List<Cancellable>) : Presenter<V> {

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
        useCases.forEach { it.cancel() }
        unbind()
    }

    override fun onInit() {}

    override fun onStart() {}
    override fun onPause() {}
    override fun onResume() {}
    override fun onStop() {}

}
