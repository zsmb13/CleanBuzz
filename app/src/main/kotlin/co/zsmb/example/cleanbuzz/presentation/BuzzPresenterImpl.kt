package co.zsmb.example.cleanbuzz.presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.base.BasePresenter
import rx.Scheduler
import javax.inject.Inject

class BuzzPresenterImpl @Inject constructor(
        private val context: Context,
        private val buzzUseCase: BuzzUseCase,
        private val ioScheduler: Scheduler,
        private val mainScheduler: Scheduler)
    : BasePresenter<BuzzView>(useCases = listOf(buzzUseCase)),
        BuzzPresenter {

    private var lastResult: PresentableResult = PresentableResult.EMPTY
    private var requestPending = false

    override fun restoreViewState() {
        showLastResult()
    }

    private fun showLastResult() {
        println("Showing $lastResult")
        view?.showResult(lastResult)
    }

    override fun requestNumber(numberText: String) {
        if (requestPending) {
            return
        }

        val number = checkInput(numberText)

        if (number != null) {
            executeUseCase(number)
        }
        else {
            println("Showing error")
            lastResult = PresentableResult(context.getString(R.string.error_number_info), isError = true)
            showLastResult()
        }
    }

    private fun checkInput(numberString: String): Int? {
        if (numberString.isBlank()) return null

        val number: Int

        try {
            number = numberString.toInt()
        } catch (e: NumberFormatException) {
            return null
        }

        return if (number > 0 && number < 1000) number else null
    }

    private fun executeUseCase(number: Int) {
        requestPending = true

        buzzUseCase.execute(worker = ioScheduler, params = number)
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
                        },
                        {
                            requestPending = false
                        }
                )
    }

}
