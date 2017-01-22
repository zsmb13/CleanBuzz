package co.zsmb.example.cleanbuzz.presentation.buzz

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.domain.usecase.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.base.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject

class BuzzPresenterImpl @Inject constructor(
        private val context: Context,
        private val buzzUseCase: BuzzUseCase,
        private val mainScheduler: Scheduler)
    : BasePresenter<BuzzView>(),
        BuzzPresenter {

    private val numberError by lazy {
        PresentableResult(context.getString(R.string.error_number_info), isError = true)
    }

    private fun showResult(result: PresentableResult) {
        view?.showResult(result)
    }

    override fun requestNumber(numberText: String) {
        val number = checkInput(numberText)

        if (number != null) {
            executeUseCase(number)
        }
        else {
            showResult(numberError)
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
        subscriptions += buzzUseCase.execute(number)
                .map { PresentableResult(it.result) }
                .observeOn(mainScheduler)
                .subscribe(
                        { result ->
                            showResult(result)
                        },
                        { error ->
                            val result =
                                    PresentableResult(
                                            error.message ?: context.getString(R.string.error_unknown),
                                            isError = true
                                    )
                            showResult(result)
                        }
                )
    }

}
