package co.zsmb.example.cleanbuzz.presentation.base

interface Presenter<in V : Any> : LifecycleObserver {

    fun bind(view: V)

    fun unbind()

}
