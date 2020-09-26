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

    @SerializedName("vn99")
    val vn99: Double? = null

    @SerializedName("vnt")
    val vnt: Double? = null

    @SerializedName("twtoken")
    val twtoken: Double? = null

    @SerializedName("twtoken_cash")
    val twtoken_cash: Double? = null

    @SerializedName("allsignal")
    val allsignal: Int? = null

    @SerializedName("signalrunning")
    val signalrunning: Int? = null

    @SerializedName("signaldonesell")
    val signaldonesell: Int? = null

    @SerializedName("signalstoploss")
    val signalstoploss: Int? = null

    @SerializedName("totalref")
    val totalref: String? = null

    @SerializedName("totaldonate")
    val totaldonate: String? = null

    @SerializedName("linkref")
    val linkref: String? = null

}