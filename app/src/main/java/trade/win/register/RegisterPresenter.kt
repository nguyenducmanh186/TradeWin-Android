package trade.win.register

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.rest.RestClient

class RegisterPresenter {
    lateinit var iRegister: IRegister

    fun register(username: String, email: String, password: String, key: String, ref: String?){
        val callAPI = RestClient.retrofitService().register(username, email, password, password, key, ref )
        callAPI.enqueue(object : Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                iRegister.onErrorFailure(t)
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
               if (response.isSuccessful){
                   if (response.body()!= null){
                       val body = response.body()!!
                       if (body.type == 0){
                           iRegister.onSuccessRegister(response.body()!!)

                       } else {
                           iRegister.onError(body.msg!!)
                       }
                   }
               } else {
                   iRegister.onError(response.message())
               }
            }

        })
    }
}