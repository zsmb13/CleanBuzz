package co.zsmb.example.cleanbuzz.presentation

import android.content.Context
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.domain.BuzzResult
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import com.nhaarman.mockito_kotlin.*
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

        buzzPresenter = BuzzPresenterImpl(
                context = context,
                buzzUseCase = usecase,
                ioScheduler = Schedulers.immediate(),
                mainScheduler = Schedulers.immediate())

        buzzPresenter.bind(view)
    }

    @Test
    fun requestNumberWithEmptyString_showsErrorInView() {
        buzzPresenter.requestNumber("")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true)
        ))
    }

    @Test
    fun requestNumberWithMinValue_showsResultInView() {
        whenever(usecase.execute(any(), any(), any())) doReturn Observable.just(BuzzResult("1"))

        buzzPresenter.requestNumber("1")

        verify(view).showResult(eq(
                PresentableResult(result = "1", isError = false)
        ))
    }

    @Test
    fun requestNumberWithMaxValue_showsResultInView() {
        whenever(usecase.execute(any(), any(), any())) doReturn Observable.just(BuzzResult("999"))

        buzzPresenter.requestNumber("999")

        verify(view).showResult(eq(
                PresentableResult(result = "999")
        ))
    }

    @Test
    fun requestNumberWithJustBelowMinValue_showsErrorInView() {
        buzzPresenter.requestNumber("0")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true)
        ))
    }

    @Test
    fun requestNumberWithJustAboveMaxValue_showsErrorInView() {
        buzzPresenter.requestNumber("1000")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true)
        ))
    }

    @Test
    fun requestNumberWithNonNumberArgument_showsErrorInView() {
        buzzPresenter.requestNumber("bunny")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true)
        ))
    }

    @Test
    fun requestNumberWithInvalidValue_showsErrorInView() {
        buzzPresenter.requestNumber("1234")

        verify(view).showResult(eq(
                PresentableResult(result = ERROR_NUMBER_INFO, isError = true)
        ))
    }

}
