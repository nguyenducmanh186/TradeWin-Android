package trade.win.register

import trade.win.login.LoginRespone

interface IRegister {
    fun onSuccessRegister(registerResponse: RegisterResponse)
    fun onErrorFailure(t: Throwable)
    fun onError(msg: String)
}