package co.zsmb.example.cleanbuzz._1_presentation.base

abstract class BasePresenter<V : Any> : Presenter<V> {

    protected var view: V? = null

    override fun bind(view: V) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun onTerminate() {
        unbind()
    }

    override fun onInit() {}

    override fun onStart() {}
    override fun onPause() {}
    override fun onResume() {}
    override fun onStop() {}

}
