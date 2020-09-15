package trade.win.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import trade.win.MainActivity
import trade.win.R
import trade.win.base.BaseActivity
import trade.win.help.MD5
import trade.win.register.RegisterActivity
import trade.win.webview.WebviewFragment

class LoginActivity : BaseActivity(), ILogin {

    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter()
        loginPresenter.iLogin = this

        initEvent()
        initAction()
    }

    private fun initEvent() {


    }

    private fun initAction() {
        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            if (isConnectedInternet()) {
                actionLogin()
            } else {
                showError("Please check internet connnection")
            }
        }
    }

    private fun actionLogin() {
        val edtUsername = edtUsername.text.toString()
        val password = edtPassword.text.toString()

        if (edtUsername.isEmpty()) {
            showError("Enter mail or username")
        } else if (password.isEmpty()) {
            showError("Enter password")
        } else {
            showProgress()
            val keyMD5 = getString(R.string.key_md5)
            val unixTime = System.currentTimeMillis() / 1000
            val passwordEncrypt = MD5().md5(password)
            val sign = MD5().md5(edtUsername+passwordEncrypt+unixTime+keyMD5)
            loginPresenter.login(edtUsername, passwordEncrypt,unixTime.toString(), sign)
        }
    }

    override fun onSuccessLogin(loginRespone: LoginRespone) {
        Toasty.success(this, loginRespone.msg!!).show()
        dismissProgress()
        Log.i("LLLLLLL", loginRespone.token_parram)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(LOGIN_REPONSE, loginRespone)
        startActivity(intent)
        finish()

    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }

    override fun onError(msg: String) {
        showError(msg)
    }

    companion object{
        const val LOGIN_REPONSE = "login-response"
    }
}