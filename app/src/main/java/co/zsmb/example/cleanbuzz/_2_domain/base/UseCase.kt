package co.zsmb.example.cleanbuzz._2_domain.base

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

abstract class UseCase<T> : Interactor<T> {

    private var subscription = Subscriptions.empty()

    protected abstract fun createObservable(): Observable<T>

    override fun execute(subscriber: Subscriber<T>) {
        this.subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    override fun execute(): Observable<T> =
            createObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

}
