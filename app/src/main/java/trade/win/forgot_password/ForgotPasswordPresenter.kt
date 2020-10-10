package trade.win.forgot_password

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.R
import trade.win.help.MD5
import trade.win.register.RegisterResponse
import trade.win.rest.RestClient

class ForgotPasswordPresenter {

    lateinit var iForgotPassword: IForgotPassword

    fun forgotPassword(email: String, context: Context){
        val key = MD5().md5(email+context.getString(R.string.value_trade_win))

        Log.i("HHHHHH", key)

        val callAPI = RestClient.retrofitService().forgotPassword(email, key)
        callAPI.enqueue(object :Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                iForgotPassword.onErrorFailure(t)
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body!= null){
                        if (body.type== 0){
                            iForgotPassword.onSuccessForgot(body)
                        } else {
                            iForgotPassword.onError(body.msg!!)
                        }
                    }
                } else {
                    iForgotPassword.onError(response.message())
                }
            }

        })


    }

}