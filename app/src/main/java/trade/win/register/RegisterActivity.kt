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
import trade.win.help.ValidateEmail
import trade.win.login.LoginActivity

class RegisterActivity : BaseActivity(), IRegister {

    private var random = ""

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
            actionRegister()

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
        val key = MD5().md5(username + password + email + "tradewin")
        val ref = edtRef.text.toString()
        val edtRandom = edtCheckRandom.text.toString()

        if (username.isEmpty()) {
            showWarning(getString(R.string.empty_username))
        } else if (email.isEmpty()) {
            showWarning(getString(R.string.empty_email))
        } else if (!ValidateEmail.isValidEmail(email)) {
            showWarning(getString(R.string.email_invalid))
        } else if (password.isEmpty()) {
            showWarning(getString(R.string.empty_password))
        } else if (password != repassword) {
            showWarning(getString(R.string.not_match_password))
        } else if (edtRandom.isEmpty()) {
            showWarning(getString(R.string.empty_capcha))
        } else if (random != edtRandom) {
            initRandom()
            showWarning(getString(R.string.validate_capcha))
        } else {
            showProgress()
            if (ref.isEmpty()) {
                registerPresenter.register(username, email, password, key, null)
            } else {
                registerPresenter.register(username, email, password, key, ref)
            }
        }
    }

    private fun showDialogRegisterSuccess() {
        val alertDialog = AlertDialog.Builder(this).create()
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_verify_email, null)
        val btnOk = view.btnOk

        alertDialog.setView(view)

        btnOk.setOnClickListener {
            dismissProgress()
            onBackPressed()
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