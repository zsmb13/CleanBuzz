package co.zsmb.example.cleanbuzz._1_presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz._2_domain.BuzzResult
import co.zsmb.example.cleanbuzz._2_domain.BuzzUseCase
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.schedulers.Schedulers

@RunWith(MockitoJUnitRunner::class)
class BuzzPresenterImplTest {

    companion object {
        private val ERROR_NUMBER_INFO = "ERROR_NUMBER_INFO"
    }

    @Mock lateinit var context: Context
    @Mock lateinit var view: BuzzView
    @Mock lateinit var usecase: BuzzUseCase

    lateinit var buzzPresenter: BuzzPresenterImpl

    @Before
    fun setUp() {
        whenever(context.getString(R.string.error_number_info)) doReturn ERROR_NUMBER_INFO

        buzzPresenter = BuzzPresenterImpl(context, usecase)
        buzzPresenter.bind(view)
    }

    @Test
    fun requestNumberWithEmptyString() {
        buzzPresenter.requestNumber("")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true))
        )
    }

    @Test
    fun requestNumberWithMinValue() {
        whenever(usecase.execute()).thenReturn(immediateResultOf("1"))

        buzzPresenter.requestNumber("1")

        verify(view).showResult(eq(
                PresentableResult(result = "1", isError = false))
        )
    }

    @Test
    fun requestNumberWithMaxValue() {
        whenever(usecase.execute()).thenReturn(immediateResultOf("999"))

        buzzPresenter.requestNumber("999")

        verify(view).showResult(eq(
                PresentableResult(result = "999", isError = false))
        )
    }

    @Test
    fun requestNumberWithJustBelowMinValue() {
        buzzPresenter.requestNumber("0")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true))
        )
    }

    @Test
    fun requestNumberWithJustAboveMaxValue() {
        buzzPresenter.requestNumber("1000")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true))
        )
    }

    @Test
    fun requestNumberWithNonNumberArgument() {
        buzzPresenter.requestNumber("bunny")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true))
        )
    }

    @Test
    fun requestNumberWithInvalidValue() {
        buzzPresenter.requestNumber("1234")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true))
        )
    }

    private fun immediateResultOf(result: String)
            = Observable.just(BuzzResult(result))
            .subscribeOn(Schedulers.immediate())
            .observeOn(Schedulers.immediate())

}
