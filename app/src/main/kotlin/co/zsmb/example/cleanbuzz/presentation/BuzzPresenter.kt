package co.zsmb.example.cleanbuzz.presentation

import co.zsmb.example.cleanbuzz.presentation.base.Presenter

interface BuzzPresenter : Presenter<BuzzView> {

    fun requestNumber(numberText: String)

}
