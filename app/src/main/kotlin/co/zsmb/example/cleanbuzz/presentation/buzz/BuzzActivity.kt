package co.zsmb.example.cleanbuzz.presentation.buzz

import android.graphics.Color
import android.os.Bundle
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz.di.application.BuzzApplication
import co.zsmb.example.cleanbuzz.di.buzz.BuzzActivityComponent
import co.zsmb.example.cleanbuzz.di.buzz.DaggerBuzzActivityComponent
import co.zsmb.example.cleanbuzz.presentation.base.BaseView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class BuzzActivity : BaseView<BuzzPresenter, BuzzActivityComponent>(), BuzzView {

    companion object {
        val warningColor = Color.RED
        val resultColor = Color.GRAY
    }

    override fun createComponent(): BuzzActivityComponent
            = DaggerBuzzActivityComponent.builder()
            .applicationComponent(BuzzApplication.applicationComponent)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind(this)

        btnBuzz.onClick {
            val input = etNumber.text.toString()
            presenter.requestNumber(input)
        }
    }

    override fun showResult(result: PresentableResult) {
        tvResult.text = result.result
        tvResult.textColor = if (result.isError) warningColor else resultColor
    }

}
