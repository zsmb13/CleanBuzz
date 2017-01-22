package co.zsmb.example.cleanbuzz.presentation.base

import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : Any> : Presenter<V> {

    protected var view: V? = null

    protected val subscriptions = mutableListOf<Disposable>()

    override fun bind(view: V) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    private fun cancelSubscriptions() {
        subscriptions.filter { !it.isDisposed }.forEach { it.dispose() }
        subscriptions.clear()
    }

    final override fun onTerminate() {
        cancelSubscriptions()
        unbind()
    }

}
