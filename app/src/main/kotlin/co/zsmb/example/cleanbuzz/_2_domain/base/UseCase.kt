package co.zsmb.example.cleanbuzz._2_domain.base

import rx.Observable
import rx.Scheduler
import rx.subscriptions.Subscriptions

abstract class UseCase<T, in P> {

    private var subscription = Subscriptions.empty()

    protected abstract fun createObservable(params: P): Observable<T>

    fun execute(worker: Scheduler, observer: Scheduler, params: P): Observable<T>
            = createObservable(params)
            .subscribeOn(worker)
            .observeOn(observer)

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

}
