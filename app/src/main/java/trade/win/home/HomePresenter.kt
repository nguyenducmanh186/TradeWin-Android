package trade.win.home

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.authenticate.UserManager
import trade.win.register.RegisterResponse
import trade.win.rest.RestClient
import java.net.URLEncoder

class HomePresenter {
    lateinit var iHome: IHome

    fun checkExpireToken(context: Context){
        val token = UserManager.getInstance(context).getToken()
        val tokenEncode = URLEncoder.encode(token)
        val callAPI = RestClient.retrofitService().checkExpireToken(tokenEncode)

        callAPI.enqueue(object :Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                iHome.onErrorFailure(t)
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        if (body.type == RestClient.CODE_EXPIRE_TOKEN){
                            iHome.onExpireToken()
                        } else {
                            iHome.onSuccess()
                        }
                    }
                } else {
                    iHome.onError(response.message())
                }
            }

        })
    }
}