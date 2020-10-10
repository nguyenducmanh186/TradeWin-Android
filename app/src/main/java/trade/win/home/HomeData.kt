package trade.win.home

import android.graphics.drawable.Drawable
import java.io.Serializable

class HomeData (
    val id : Int ,
    val name : String,
    val icon : Drawable?
    ) : Serializable