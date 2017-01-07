package co.zsmb.example.cleanbuzz._1_presentation.base

interface LifecycleObserver {

    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()

    fun onInit()
    fun onTerminate()

}
