package trade.win.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import trade.win.BuildConfig

object RestClient {
    const val CODE_SUCCESS = 200
    const val CODE_ERROR = 403
    const val CODE_EXPIRE_TOKEN = 405
    const val CODE_EXPIRE_PASSWORD = 499
    const val CODE_INTERNAL = 500

    fun retrofitService(): RestService {
        val gson = GsonBuilder().setLenient().create()
        val service = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(SSLOkHttpClient.unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RestService::class.java)
        return service
    }

}
