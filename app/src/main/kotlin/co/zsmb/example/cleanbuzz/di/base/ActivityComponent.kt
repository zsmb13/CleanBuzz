package co.zsmb.example.cleanbuzz.di.base

interface ActivityComponent<out PR> {

    fun createPresenter(): PR

}
