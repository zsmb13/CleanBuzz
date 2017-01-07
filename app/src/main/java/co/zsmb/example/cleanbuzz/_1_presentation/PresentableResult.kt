package co.zsmb.example.cleanbuzz._1_presentation

data class PresentableResult(val result: String, val isError: Boolean = false) {

    companion object {
        val EMPTY = PresentableResult("")
    }

}
