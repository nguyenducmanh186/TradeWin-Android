package trade.win.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.dialog_verify_email.view.*
import trade.win.R
import trade.win.base.BaseActivity
import trade.win.help.MD5
import trade.win.help.RandomHelper
import trade.win.login.LoginActivity

class RegisterActivity: BaseActivity() , IRegister{

    private var random= ""

    lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerPresenter = RegisterPresenter()
        registerPresenter.iRegister = this

        initRandom()
        initAction()
    }

    private fun initAction() {
        btnRegister.setOnClickListener {


            val edtRandom = edtCheckRandom.text.toString()
            if (random != edtRandom){
                showError("Validate not match")
                initRandom()
            } else {
                actionRegister()
            }
        }

        btnReFresh.setOnClickListener {
            initRandom()
        }
    }

    private fun actionRegister() {
        val username = edtUsername_register.text.toString()
        val email = edtEmail_register.text.toString()
        val password = edtPassword_register.text.toString()
        val repassword = edtRePassword_register.text.toString()
        val key = MD5().md5(username+password+email+"tradewin")
        if (username.isEmpty()){
            showWarning(getString(R.string.empty_username))
        } else if (email.isEmpty()){
            showWarning(getString(R.string.empty_email))
        } else if (password.isEmpty()){
            showWarning(getString(R.string.empty_password))
        } else if (password!= repassword){
            showWarning(getString(R.string.not_match_password))
        } else {
            showProgress()
            registerPresenter.register(username, email, password, key ,null)
        }
    }

    private fun showDialogRegisterSuccess(){
        val alertDialog = AlertDialog.Builder(this).create()
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_verify_email, null)
        val btnOk = view.btnOk

        alertDialog.setView(view)

        btnOk.setOnClickListener {
            dismissProgress()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        alertDialog.show()
        alertDialog.setCancelable(false)

    }

    private fun initRandom() {
        random = RandomHelper.getRandomString()
        txtRandom.text = random
    }

    override fun onSuccessRegister(registerResponse: RegisterResponse) {
        showDialogRegisterSuccess()
    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }

    override fun onError(msg: String) {
        showError(msg)
    }
}