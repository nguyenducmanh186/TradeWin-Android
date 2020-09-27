package trade.win.invitation

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class InvitationResponse: Serializable {

    @SerializedName("type")
    val type: Int? = null

    @SerializedName("ref1")
    val ref1: Int? = null

    @SerializedName("ref2")
    val ref2: Int? = null

    @SerializedName("ref3")
    val ref3: Int? = null

    @SerializedName("ref4")
    val ref4: Int? = null

    @SerializedName("ref5")
    val ref5: Int? = null

    @SerializedName("tongtienref")
    val tongtienref: Int? = null
}