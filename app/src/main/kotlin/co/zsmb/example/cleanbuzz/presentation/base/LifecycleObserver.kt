package co.zsmb.example.cleanbuzz.presentation.base

interface LifecycleObserver {

    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()

    fun onTerminate()

}
