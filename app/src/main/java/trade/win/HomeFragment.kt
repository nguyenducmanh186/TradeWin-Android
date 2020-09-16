package trade.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import trade.win.base.BaseFragment

class HomeFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()
    }

    private fun initAction() {
        layoutTradeWin.setOnClickListener {
            findNavController().navigate(R.id.navigate_fragment_from_home_to_trade_win)
        }

        layoutNotification.setOnClickListener {
            showWarning("Not yet implement")
        }

        layoutAccount.setOnClickListener {
            showWarning("Not yet implement")
        }
    }
}