package alektas.tinkoffnews.ui

data class ProcessState(val state: Int) {
    companion object {
        const val STARTED = 1
        const val SUCCESS = 2
        const val ERROR = 3
    }
}
