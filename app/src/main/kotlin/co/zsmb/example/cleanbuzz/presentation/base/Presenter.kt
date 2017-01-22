package co.zsmb.example.cleanbuzz.presentation.base

interface Presenter<in V : Any> : Terminable {

    fun bind(view: V)

    fun unbind()

}
