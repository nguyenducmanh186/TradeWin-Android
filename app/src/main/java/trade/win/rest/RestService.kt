package trade.win.rest

import retrofit2.Call
import retrofit2.http.*
import trade.win.history.HistoryResponse
import trade.win.invitation.InvitationResponse
import trade.win.login.LoginRespone
import trade.win.register.RegisterResponse

interface RestService {

    @GET("login.php")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("time") time: String,
        @Query("sign") sign: String,
        @Query("fcm_token") fcm_token: String,
        @Query("device") device: String
    ): Call<LoginRespone>

    @GET("gethistory.php")
    fun history(@Query("token_parram") token: String): Call<List<HistoryResponse>>

    @GET("register.php")
    fun register(
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String,
        @Query("key") key: String,
        @Query("ref") ref: String?
    ): Call<RegisterResponse>

    @GET("forgot-password.php")
    fun forgotPassword(
        @Query("email") email: String,
        @Query("key") key: String
    ): Call<RegisterResponse>

    @GET("check_token.php")
    fun checkExpireToken(@Query("token_parram") token_parram: String): Call<RegisterResponse>

    @GET("getref.php")
    fun getInvitation(@Query("token_parram") token_parram: String): Call<InvitationResponse>
}