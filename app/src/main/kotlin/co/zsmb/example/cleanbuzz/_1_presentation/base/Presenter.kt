package co.zsmb.example.cleanbuzz._1_presentation.base

interface Presenter<in V : Any> : LifecycleObserver {

    fun bind(view: V)

    fun unbind()

}
