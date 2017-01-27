package co.zsmb.example.cleanbuzz.presentation.base.util

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class ReplayOneSubject<T : Any>(val observeOn: Scheduler) {

    private val subject: PublishSubject<T> = PublishSubject.create()
    private val replayedSubject = subject.replay(1)

    private var disposable = Disposables.empty()

    init {
        replayedSubject.connect()
    }

    fun subscribe(consumerFunction: (T) -> Unit) {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        val consumer = Consumer<T>(consumerFunction)
        disposable = replayedSubject
                .observeOn(observeOn)
                .subscribe(consumer)
    }

    fun unsubscribe() {
        disposable.dispose()
    }

    fun onNext(value: T) {
        subject.onNext(value)
    }

}
