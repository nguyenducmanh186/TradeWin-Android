package trade.win.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import trade.win.R

class HistoryAdapter(val listHistory : ArrayList<HistoryData>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.apply {
            bind(listHistory[position])
        }
    }


    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val txtTime = item.txtTime_itemHistory
        val txtContent = item.txtContent_itemHistory
        fun bind(item: HistoryData){
            txtTime.text = item.date_time
            txtContent.text = item.content

        }

    }

}