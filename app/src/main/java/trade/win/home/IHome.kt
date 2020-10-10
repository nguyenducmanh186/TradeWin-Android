package trade.win.home

interface IHome {
    fun onExpireToken()
    fun onErrorFailure(t: Throwable)
    fun onError(msg: String)
    fun onSuccess()

}