package trade.win.invitation

import android.R.attr.label
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_invitation.*
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.App
import trade.win.R
import trade.win.authenticate.UserManager
import trade.win.base.BaseFragment
import trade.win.help.SharedPreferencesHelper


class InvitationFragment: BaseFragment() , IInvitation{

    lateinit var invitationPresenter: InvitationPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_invitation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderToolbar.text = getString(R.string.header_invitation)

        invitationPresenter =  InvitationPresenter()
        invitationPresenter.iInvitation = this

        initAction()
        callAPI()
    }

    private fun callAPI() {
        if (isConnectedInternet(App.applicationContext())){
            showProgress()
            invitationPresenter.getInvitation(App.applicationContext())
        } else {
            showWarning(getString(R.string.check_network))
        }
    }

    private fun initAction() {
        btnBack.setOnClickListener {
            (context as Activity).onBackPressed()
        }

        val linkRef = SharedPreferencesHelper(App.applicationContext()).getLoginData()?.linkref
        txtLink.text = linkRef
        btnClipBoard.setOnClickListener {
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Invitation Link", linkRef)
            clipboard.setPrimaryClip(clip)
            showSuccess("Invitation link has copied")
        }


    }

    override fun onSuccess(ivitation: InvitationResponse) {
        dismissProgress()
        txtLevel1.text = ivitation.ref1.toString() + " Level 1"
        txtLevel2.text = "● "+ivitation.ref2.toString() + " Level 2 - " + ivitation.ref3 + " Level 3"
        txtLevel4.text = "● "+ivitation.ref4.toString() + " Level 4 - " + ivitation.ref5 + " Level 5"
        txtTotalYouEarned.text = if (ivitation.tongtienref!= null) ivitation.tongtienref.toString() else "0"
    }

    override fun onError() {
        dismissProgress()
    }

    override fun onError(msg: String) {
        showError(msg)
    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }

    override fun onExpireToken() {
        expireToken(App.applicationContext())
    }
}