package trade.win.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import trade.win.App
import trade.win.R
import trade.win.base.BaseFragment

class HomeFragment : BaseFragment(), IHome{
    lateinit var homePresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePresenter= HomePresenter()
        homePresenter.iHome = this

        initAction()
        checkExpireToken()
    }

    private fun checkExpireToken() {
        if (isConnectedInternet(App.applicationContext())){
            homePresenter.checkExpireToken(App.applicationContext())
        }
    }

    private fun initAction() {
        layoutTradeWin.setOnClickListener {
            findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win)
        }

        layoutNotification.setOnClickListener {
            findNavController().navigate(R.id.navigate_fragment_from_home_to_history)

        }

        layoutAccount.setOnClickListener {
            findNavController().navigate(R.id.navigate_fragment_from_home_to_account)

        }

        layoutInvatation.setOnClickListener {
            findNavController().navigate(R.id.navigate_fragment_from_home_to_invitation)

        }
    }

    override fun onExpireToken() {
        expireToken(App.applicationContext())
    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }

    override fun onError(msg: String) {
        showError(msg)
    }

    override fun onSuccess() {
        dismissProgress()
    }
}