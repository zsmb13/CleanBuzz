package co.zsmb.example.cleanbuzz.presentation.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : Any> : Presenter<V> {

    protected var view: V? = null

    protected val subscriptions = CompositeDisposable()

    override fun bind(view: V) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    final override fun onTerminate() {
        subscriptions.dispose()
        unbind()
    }

}
