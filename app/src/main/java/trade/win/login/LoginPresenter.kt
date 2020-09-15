package trade.win.login

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.rest.RestClient

class LoginPresenter {
    lateinit var iLogin: ILogin

    fun login(email: String, password: String, time: String, sign: String){
        val callAPI = RestClient.retrofitService().login(email, password,time, sign)
        callAPI.enqueue(object :Callback<LoginRespone>{
            override fun onFailure(call: Call<LoginRespone>, t: Throwable) {
                iLogin.onErrorFailure(t)
            }

            override fun onResponse(call: Call<LoginRespone>, response: Response<LoginRespone>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body!= null){
                        if (body.status == 1){
                            iLogin.onSuccessLogin(body)
                        } else {
                            iLogin.onError(body.msg!!)
                        }
                    }
                } else {
                    iLogin.onError(response.message())
                }
            }

        })

    }

}