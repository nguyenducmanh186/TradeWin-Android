package trade.win.forgot_password

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.R
import trade.win.base.BaseActivity
import trade.win.help.RandomHelper
import trade.win.help.ValidateEmail
import trade.win.register.RegisterResponse


class ForgotPasswordActivity: BaseActivity(), IForgotPassword {
    lateinit var forgotPasswordPresenter: ForgotPasswordPresenter
    private var random =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        txtHeaderToolbar.text = getString(R.string.header_forgot_password)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        forgotPasswordPresenter = ForgotPasswordPresenter()
        forgotPasswordPresenter.iForgotPassword = this

        initAction()
        initRandom()

    }

    private fun initRandom() {
        random = RandomHelper.getRandomString()
        txtRandom_forgot.text = random
    }

    private fun initAction() {
        btnForgotPassword.setOnClickListener {
            actionForgot()
        }

        btnReFresh_forgot.setOnClickListener {
            initRandom()
        }
    }



    private fun actionForgot() {
        val email = edtEmail_forgot.text.toString()
        val capcha = edtCheckRandom_forgot.text.toString()

        if (email.isEmpty()){
            showWarning(getString(R.string.empty_email))
        } else if (!ValidateEmail.isValidEmail(email)){
            showWarning(getString(R.string.email_invalid))
        } else if (capcha.isEmpty()){
            showWarning(getString(R.string.empty_capcha))
        } else if (random != capcha){
            initRandom()
            showWarning(getString(R.string.validate_capcha))
        } else if (isConnectedInternet()) {
            showProgress()
            forgotPasswordPresenter.forgotPassword(email, this)
        }
    }

    override fun onSuccessForgot(registerResponse: RegisterResponse) {
        showSuccess(registerResponse.msg!!)
        onBackPressed()
    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }

    override fun onError(msg: String) {
        showError(msg)
    }
}