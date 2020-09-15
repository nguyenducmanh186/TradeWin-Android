package trade.win.rest

import retrofit2.Call
import retrofit2.http.*
import trade.win.login.LoginRespone

interface RestService {

    @GET("api/login.php")
    fun login(@Query("username") username: String,
                    @Query("password") password: String,
                    @Query("time") time: String ,
                    @Query("sign") sign: String  ): Call<LoginRespone>

//    @POST("login")
//    @FormUrlEncoded
//    fun login(
//        @Field("phone") numberphone: String): Call<LoginResponse>
}