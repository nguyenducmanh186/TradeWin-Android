package trade.win.register

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import trade.win.R
import trade.win.base.BaseActivity
import trade.win.help.RandomHelper

class RegisterActivity: BaseActivity() {

    private var random= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initRandom()
        initAction()
    }

    private fun initAction() {
        btnRegister.setOnClickListener {
            val edtRandom = edtCheckRandom.text.toString()
            if (random != edtRandom){
                showError("Validate not match")
                initRandom()
            }
        }

        btnReFresh.setOnClickListener {
            initRandom()
        }
    }

    private fun initRandom() {
        random = RandomHelper.getRandomString()
        txtRandom.text = random
    }
}