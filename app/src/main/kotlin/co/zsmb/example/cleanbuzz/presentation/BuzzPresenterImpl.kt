package co.zsmb.example.cleanbuzz.presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.base.BasePresenter
import rx.Scheduler
import rx.Subscription
import javax.inject.Inject

class BuzzPresenterImpl @Inject constructor(
        private val context: Context,
        private val buzzUseCase: BuzzUseCase,
        private val ioScheduler: Scheduler,
        private val mainScheduler: Scheduler)
    : BasePresenter<BuzzView>(),
        BuzzPresenter {

    private var lastResult: PresentableResult = PresentableResult.EMPTY
    private var subscription: Subscription? = null

    override fun restoreViewState() {
        showLastResult()
    }

    private fun showLastResult() {
        view?.showResult(lastResult)
    }

    override fun requestNumber(numberText: String) {
        val number = checkInput(numberText)

        if (number != null) {
            executeUseCase(number)
        }
        else {
            lastResult = PresentableResult(context.getString(R.string.error_number_info), isError = true)
            showLastResult()
        }
    }

    private fun checkInput(numberString: String): Int? {
        if (numberString.isBlank()) return null

        val number: Int = numberString.toIntOrNull() ?: return null

        return if (number in 1..999) number else null
    }

    private fun String.toIntOrNull(): Int? {
        try {
            return this.toInt()
        } catch (e: NumberFormatException) {
            return null
        }
    }

    private fun executeUseCase(number: Int) {
        subscription?.let { it.unsubscribe() }

        subscription = buzzUseCase.execute(worker = ioScheduler, params = number)
                .map { PresentableResult(it.result) }
                .observeOn(mainScheduler)
                .subscribe(
                        { it ->
                            lastResult = it
                            showLastResult()
                        },
                        { error ->
                            lastResult =
                                    PresentableResult(
                                            error.message ?: context.getString(R.string.error_unknown),
                                            isError = true
                                    )
                            showLastResult()
                            subscription = null
                        },
                        {
                            subscription = null
                        }
                )
    }

    override fun onTerminate() {
        super.onTerminate()
        subscription?.unsubscribe()
    }

}
