package co.zsmb.example.cleanbuzz._2_domain.base

import rx.Observable
import rx.Subscriber

interface Interactor<T> {

    fun execute(subscriber: Subscriber<T>)

    fun execute(): Observable<T>

}
