package co.zsmb.example.cleanbuzz.presentation.buzz

data class PresentableResult(val result: String, val isError: Boolean = false) {

    companion object {
        val EMPTY = PresentableResult("")
    }

}
