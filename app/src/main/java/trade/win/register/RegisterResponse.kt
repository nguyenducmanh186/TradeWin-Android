package trade.win.register

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RegisterResponse: Serializable {
    @SerializedName("type")
    val type: Int? = null

    @SerializedName("msg")
    val msg: String? = null
}