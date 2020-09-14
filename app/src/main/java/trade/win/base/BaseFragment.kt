package trade.win.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    open fun showProgress() {
//   CustomProgressDialog.newInstance().progressDialogStart(context)
    }

    open fun dismissProgress() {
//   CustomProgressDialog.newInstance().progressDialogStop()
    }

    open fun isConnectedInternet(context: Context): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val infos = connectivityManager.allNetworkInfo
            if (infos != null) {
                for (i in infos.indices) {
                    if (infos[i].state == NetworkInfo.State.CONNECTED)
                        return true
                }
            }
        }
        return false
    }


    open fun onBackPressed(): Boolean {
        return false
    }


}