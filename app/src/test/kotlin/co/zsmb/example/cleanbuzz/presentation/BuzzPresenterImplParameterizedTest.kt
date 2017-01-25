package co.zsmb.example.cleanbuzz.presentation

import co.zsmb.example.cleanbuzz.domain.BuzzResult
import co.zsmb.example.cleanbuzz.domain.usecase.BuzzUseCase
import co.zsmb.example.cleanbuzz.presentation.buzz.BuzzPresenterImpl
import co.zsmb.example.cleanbuzz.presentation.buzz.BuzzView
import co.zsmb.example.cleanbuzz.presentation.buzz.PresentableResult
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

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
                mainScheduler = Schedulers.trampoline())

        buzzPresenter.bind(view)
    }

    @Test
    fun requestNumberWithValidValue_showsResultInView() {
        whenever(usecase.execute(any())) doReturn Single.just(BuzzResult(input))

        buzzPresenter.requestNumber(input)

        verify(view).showResult(PresentableResult(result = input, isError = false))
    }

    companion object {
        @Parameterized.Parameters @JvmStatic
        fun data() = listOf("1", "2", "3", "42", "50", "100", "500", "750", "900", "999")
    }

}
