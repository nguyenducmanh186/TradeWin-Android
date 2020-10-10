package trade.win.invitation

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.authenticate.UserManager
import trade.win.rest.RestClient
import java.net.URLEncoder

class InvitationPresenter {

    lateinit var iInvitation: IInvitation

    fun getInvitation(context: Context){
        val token = UserManager.getInstance(context).getToken()
        val tokenEncode = URLEncoder.encode(token)

        val callAPI = RestClient.retrofitService().getInvitation(tokenEncode)
        callAPI.enqueue(object : Callback<InvitationResponse>{
            override fun onFailure(call: Call<InvitationResponse>, t: Throwable) {
                iInvitation.onErrorFailure(t)
            }

            override fun onResponse(
                call: Call<InvitationResponse>,
                response: Response<InvitationResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        when(body.type){
                            RestClient.CODE_EXPIRE_TOKEN-> {
                                iInvitation.onExpireToken()
                            }

                            0 -> {
                                iInvitation.onSuccess(body)
                            }

                            else -> {
                                iInvitation.onError()
                            }

                        }
                    }
                } else {
                    iInvitation.onError(response.message())
                }
            }

        })
    }
}