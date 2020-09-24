package trade.win.rest

import retrofit2.Call
import retrofit2.http.*
import trade.win.history.HistoryResponse
import trade.win.login.LoginRespone

interface RestService {

    @GET("login.php")
    fun login(@Query("username") username: String,
                    @Query("password") password: String,
                    @Query("time") time: String ,
                    @Query("sign") sign: String  ): Call<LoginRespone>

    @GET("gethistory.php")
    fun history(@Query("token_parram") token: String) : Call<List<HistoryResponse>>
}