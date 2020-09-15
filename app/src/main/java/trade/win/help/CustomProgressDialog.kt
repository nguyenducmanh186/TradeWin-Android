package trade.win.help

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.TextView
import trade.win.R

class CustomProgressDialog {

    private var progressDialog: ProgressDialog? = null
    private var txtSub: TextView? = null
    /**
     * Start progress dialog.
     *
     * @param ctx context
     */
    fun progressDialogStart(ctx: Context?) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            return
        }
        if (ctx == null) {
            return
        }
        progressDialog = ProgressDialog(ctx)
        try {
            progressDialog!!.show()
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }

        progressDialog!!.isIndeterminate = false
        progressDialog!!.setCancelable(false)
        progressDialog!!.setOnCancelListener(null)
        progressDialog!!.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        progressDialog!!.setContentView(R.layout.progressdialog)

    }

    /**
     * Stop the progress dialog.
     */
    fun progressDialogStop() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    companion object {
        private var sShowDialog: CustomProgressDialog? = null

        /**
         * Get dialog instance.
         *
         * @return an instance of dialog
         */
        fun newInstance(): CustomProgressDialog {
            if (sShowDialog == null) {
                sShowDialog = CustomProgressDialog()
            }
            return sShowDialog as CustomProgressDialog
        }
    }
}