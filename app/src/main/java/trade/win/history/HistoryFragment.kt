package trade.win.history

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_web_view.*
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.App
import trade.win.R
import trade.win.authenticate.UserManager
import trade.win.base.BaseFragment
import java.net.URLEncoder

class HistoryFragment : BaseFragment(), IHistory{

    lateinit var historyPresenter: HistoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderToolbar.text = getString(R.string.title_history)

        historyPresenter = HistoryPresenter()
        historyPresenter.iHistory = this

        callHistory()
        initAction()
        pullToReFreshLayout()

    }

    private fun pullToReFreshLayout() {
        swipeReFreshHistory.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        swipeReFreshHistory.setOnRefreshListener {
           callHistory()
        }


    }

    private fun initList(historyResponse: HistoryResponse) {
        recyclerViewHistory.layoutManager = LinearLayoutManager(App.applicationContext())
        recyclerViewHistory.setHasFixedSize(true)
        recyclerViewHistory.adapter = HistoryAdapter(historyResponse.listHistory!!)
        swipeReFreshHistory.isRefreshing = false
    }

    private fun callHistory() {
        if (isConnectedInternet(App.applicationContext())){
            showProgress()
            val token = UserManager.getInstance(App.applicationContext()).getToken()
            val tokenEncode = URLEncoder.encode(token)
            historyPresenter.getHistory(tokenEncode)
        } else {
            showWarning(getString(R.string.check_network))
        }
    }

    private fun initAction() {
        btnBack.setOnClickListener {
            (context as Activity).onBackPressed()
        }
    }

    override fun onSuccess(historyResponse: List<HistoryResponse>) {
        dismissProgress()
        if (historyResponse.size >0){
            initList(historyResponse[0])
        }
    }

    override fun onError(error: String) {
        showError(error)
    }

    override fun onErrorFailure(t: Throwable) {
        showOnFailureException(t)
    }
}