package co.zsmb.example.cleanbuzz.domain.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class UseCase<T, in P>(private val scheduler: Scheduler) {

    protected abstract fun createObservable(params: P): Single<T>

    fun execute(params: P): Single<T>
            = createObservable(params)
            .subscribeOn(scheduler)

}
