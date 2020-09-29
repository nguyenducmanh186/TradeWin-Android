package trade.win.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import trade.win.App
import trade.win.R
import trade.win.base.BaseFragment
import trade.win.webview.WebviewFragment
import trade.win.webview.WebviewFragment.Companion.HOME

class HomeFragment : BaseFragment(), IHome, HomeAdapter.IOnClickItemHome {
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
        homePresenter = HomePresenter()
        homePresenter.iHome = this

        initAction()
        initDataHome()
        checkExpireToken()
    }

    private fun initDataHome() {
        val listHome = ArrayList<HomeData>()
        listHome.add(
            HomeData(
                TRADEWIN,
                getString(R.string.header_trade_win),
                context?.getDrawable(R.mipmap.ic_tradewin)
            )
        )
        listHome.add(
            HomeData(
                HISTORY,
                getString(R.string.title_history),
                context?.getDrawable(R.mipmap.ic_history)
            )
        )
        listHome.add(
            HomeData(
                INVITATION,
                getString(R.string.header_invitation),
                context?.getDrawable(R.mipmap.ic_invitation)
            )
        )
        listHome.add(
            HomeData(
                ACCOUNT,
                getString(R.string.header_account_manager),
                context?.getDrawable(R.mipmap.ic_account)
            )
        )
        listHome.add(
            HomeData(
                ADD_SIGNAL,
                getString(R.string.header_add_signal),
                context?.getDrawable(R.mipmap.ic_tradewin)
            )
        )
        listHome.add(
            HomeData(
                BALANCES,
                getString(R.string.header_balances),
                context?.getDrawable(R.mipmap.ic_tradewin)
            )
        )
        listHome.add(
            HomeData(
                MEMBER_SHIP,
                getString(R.string.header_membership),
                context?.getDrawable(R.mipmap.ic_tradewin)
            )
        )
        listHome.add(
            HomeData(
                STAKING,
                getString(R.string.header_staking),
                context?.getDrawable(R.mipmap.ic_tradewin)
            )
        )

        recyclerViewHome.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewHome.setHasFixedSize(true)
        recyclerViewHome.adapter = HomeAdapter(listHome, this)
    }

    private fun checkExpireToken() {
        if (isConnectedInternet(App.applicationContext())) {
            showProgress()
            homePresenter.checkExpireToken(App.applicationContext())
        } else {
            showWarning(getString(R.string.check_network))
        }
    }

    private fun initAction() {
//        layoutTradeWin.setOnClickListener {
//            findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win)
//        }
//
//        layoutNotification.setOnClickListener {
//            findNavController().navigate(R.id.navigate_fragment_from_home_to_history)
//
//        }
//
//        layoutAccount.setOnClickListener {
//            findNavController().navigate(R.id.navigate_fragment_from_home_to_account)
//
//        }
//
//        layoutInvatation.setOnClickListener {
//            findNavController().navigate(R.id.navigate_fragment_from_home_to_invitation)
//
//        }
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

    override fun onClickItemHome(id: Int) {
        val bundle = Bundle()

        when (id){
            TRADEWIN -> {
                bundle.putString(WebviewFragment.URL, WebviewFragment.HOME)
                findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win, bundle)
            }

            HISTORY -> {
                findNavController().navigate(R.id.navigate_fragment_from_home_to_history)
            }

            INVITATION -> {
                findNavController().navigate(R.id.navigate_fragment_from_home_to_invitation)

            }

            ACCOUNT -> {
                findNavController().navigate(R.id.navigate_fragment_from_home_to_account)
            }

            ADD_SIGNAL -> {
                bundle.putString(WebviewFragment.URL, WebviewFragment.ADD_SIGNAL)
                findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win, bundle)
            }

            BALANCES -> {
                bundle.putString(WebviewFragment.URL, WebviewFragment.BALANCES)
                findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win, bundle)
            }

            MEMBER_SHIP -> {
                bundle.putString(WebviewFragment.URL, WebviewFragment.MEMBER_SHIP)
                findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win, bundle)
            }

            STAKING -> {
                bundle.putString(WebviewFragment.URL, WebviewFragment.STAKING)
                findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win, bundle)
            }
        }
    }

    companion object {
        const val  TRADEWIN = 1
        const val  HISTORY = 2
        const val  INVITATION = 3
        const val  ACCOUNT = 4
        const val  ADD_SIGNAL = 5
        const val  BALANCES = 6
        const val  MEMBER_SHIP = 7
        const val  STAKING = 8

    }
}