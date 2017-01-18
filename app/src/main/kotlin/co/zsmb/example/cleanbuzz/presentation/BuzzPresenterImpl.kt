package co.zsmb.example.cleanbuzz.presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.presentation.base.BasePresenter
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import rx.Scheduler
import javax.inject.Inject

class BuzzPresenterImpl @Inject constructor(
        private val context: Context,
        private val buzzUseCase: BuzzUseCase,
        private val ioScheduler: Scheduler,
        private val mainScheduler: Scheduler)
    : BasePresenter<BuzzView>(), BuzzPresenter {

    private var lastResult = PresentableResult.EMPTY

    override fun bind(view: BuzzView) {
        super.bind(view)
        updateView()
    }

    override fun onTerminate() {
        super.onTerminate()
        buzzUseCase.unsubscribe()
    }

    override fun requestNumber(numberText: String) {
        val number = checkInput(numberText)

        if (number != null) {
            executeUseCase(number)
        }
        else {
            displayError(context.getString(R.string.error_number_info))
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
        buzzUseCase.execute(worker = ioScheduler, observer = mainScheduler, params = number)
                .map { PresentableResult(it.result) }
                .subscribe(
                        { it ->
                            lastResult = it
                            updateView()
                        },
                        { error ->
                            displayError(error.message ?: context.getString(R.string.error_network))
                        }
                )
    }

    private fun displayError(message: String = context.getString(R.string.error_unknown)) {
        lastResult = PresentableResult(message, isError = true)
        updateView()
    }

    private fun updateView() {
        view?.showResult(lastResult)
    }

}