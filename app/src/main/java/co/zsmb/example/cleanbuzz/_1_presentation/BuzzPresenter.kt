package co.zsmb.example.cleanbuzz._1_presentation

import co.zsmb.example.cleanbuzz._1_presentation.base.Presenter

interface BuzzPresenter : Presenter<BuzzView> {

    fun requestNumber(numberText: String)

}
