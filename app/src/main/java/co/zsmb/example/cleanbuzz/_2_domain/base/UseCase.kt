package co.zsmb.example.cleanbuzz._2_domain.base

import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

abstract class UseCase<T>(
        private val ioScheduler: Scheduler,
        private val uiScheduler: Scheduler) : Interactor<T> {

    private var subscription = Subscriptions.empty()

    protected abstract fun createObservable(): Observable<T>

    override fun execute(subscriber: Subscriber<T>) {
        this.subscription = createObservable()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber)
    }

    override fun execute(): Observable<T> =
            createObservable()
                    .subscribeOn(ioScheduler)
                    .observeOn(uiScheduler)

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

}
