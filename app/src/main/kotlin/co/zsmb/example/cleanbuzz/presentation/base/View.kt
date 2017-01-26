package co.zsmb.example.cleanbuzz.presentation.base

interface View {

    fun close()

    fun showMessage(message: String)

    fun showError(error: String)

}
