package co.zsmb.example.cleanbuzz._1_presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz._1_presentation.base.BasePresenter
import co.zsmb.example.cleanbuzz._2_domain.BuzzUseCase
import javax.inject.Inject

class BuzzPresenterImpl @Inject constructor(val context: Context, val buzzUseCase: BuzzUseCase)
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

        val number = numberString.toInt()
        return if (number > 0 && number < 1000) number else null
    }

    private fun executeUseCase(number: Int) {
        buzzUseCase.number = number
        buzzUseCase.execute()
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
