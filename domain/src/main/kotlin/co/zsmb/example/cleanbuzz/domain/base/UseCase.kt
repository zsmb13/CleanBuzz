package co.zsmb.example.cleanbuzz.domain.base

import rx.Observable
import rx.Scheduler

abstract class UseCase<T, in P> {

    protected abstract fun createObservable(params: P): Observable<T>

    fun execute(worker: Scheduler, params: P): Observable<T>
            = createObservable(params)
            .subscribeOn(worker)

}
