package trade.win.history

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HistoryResponse : Serializable {

    @SerializedName("history")
    val listHistory: ArrayList<HistoryData>? = null


}

class HistoryData : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("content")
    val content: String? = null

    @SerializedName("date_time")
    val date_time: String? = null
}