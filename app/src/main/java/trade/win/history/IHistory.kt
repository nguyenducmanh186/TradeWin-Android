package trade.win.history

interface IHistory {
    fun onSuccess(historyResponse: List<HistoryResponse>)
    fun onError(error: String)
    fun onErrorFailure(t: Throwable)
    fun onExpireToken()
}