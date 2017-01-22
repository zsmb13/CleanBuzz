package co.zsmb.example.cleanbuzz.domain.base

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class UseCase<T, in P>(private val scheduler: Scheduler) {

    protected abstract fun createObservable(params: P): Observable<T>

    fun execute(params: P): Observable<T>
            = createObservable(params)
            .subscribeOn(scheduler)

}
