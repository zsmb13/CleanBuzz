package co.zsmb.example.cleanbuzz.domain.base

import rx.Observable
import rx.Scheduler
import rx.subscriptions.Subscriptions

abstract class UseCase<T, in P>: Cancellable {

    private var subscription = Subscriptions.empty()

    protected abstract fun createObservable(params: P): Observable<T>

    fun execute(worker: Scheduler, params: P): Observable<T>
            = createObservable(params)
            .subscribeOn(worker)

    override fun cancel() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

}
