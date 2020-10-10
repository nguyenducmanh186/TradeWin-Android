package trade.win.forgot_password

import trade.win.register.RegisterResponse

interface IForgotPassword {
    fun onSuccessForgot(registerResponse: RegisterResponse)
    fun onErrorFailure(t: Throwable)
    fun onError(msg: String)
}