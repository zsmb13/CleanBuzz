package co.zsmb.example.cleanbuzz.presentation

import co.zsmb.example.cleanbuzz.domain.BuzzResult
import co.zsmb.example.cleanbuzz.domain.BuzzUseCase
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import rx.Observable
import rx.schedulers.Schedulers

/* This tests that values that are in the valid range are accepted by the Presenter
 * and it fetches results for them from the UseCase as expected */
@RunWith(Parameterized::class)
class BuzzPresenterImplParameterizedTest(val input: String) {

    @get: Rule val rule: MockitoRule = MockitoJUnit.rule()

    @Mock lateinit var view: BuzzView
    @Mock lateinit var usecase: BuzzUseCase

    lateinit var buzzPresenter: BuzzPresenterImpl

    @Before
    fun setUp() {
        buzzPresenter = BuzzPresenterImpl(
                context = mock(),
                buzzUseCase = usecase,
                ioScheduler = Schedulers.immediate(),
                mainScheduler = Schedulers.immediate())

        buzzPresenter.bind(view)
    }

    @Test
    fun requestNumberWithValidValue_showsResultInView() {
        whenever(usecase.execute(any(), any())) doReturn Observable.just(BuzzResult(input))

        buzzPresenter.requestNumber(input)

        verify(view).showResult(PresentableResult(result = input, isError = false))
    }

    companion object {
        @Parameterized.Parameters @JvmStatic
        fun data() = listOf("1", "2", "3", "42", "50", "100", "500", "750", "900", "999")
    }

}
