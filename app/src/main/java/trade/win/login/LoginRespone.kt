package trade.win.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginRespone: Serializable {
    @SerializedName("status")
    val status: Int? = null

    @SerializedName("msg")
    val msg: String? = null

    @SerializedName("token_parram")
    val token_parram: String? = null

}