package trade.win.login

interface ILogin {
    fun onSuccessLogin(loginRespone: LoginRespone)
    fun onErrorFailure(t: Throwable)
    fun onError(msg: String)
}