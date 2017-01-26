package co.zsmb.example.cleanbuzz.presentation.base

interface ActivityComponent<out PR> {

    fun createPresenter(): PR

}
