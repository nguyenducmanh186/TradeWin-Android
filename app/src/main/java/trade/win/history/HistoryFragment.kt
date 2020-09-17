package trade.win.history

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.R
import trade.win.base.BaseFragment

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

        initAction()

    }

    private fun initAction() {
        btnBack.setOnClickListener {
            (context as Activity).onBackPressed()
        }
    }
}