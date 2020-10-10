package trade.win.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home.view.*
import trade.win.R

class HomeAdapter(val listHome: ArrayList<HomeData>, val onItemClick: IOnClickItemHome): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return  listHome.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(listHome[position])
        }

        holder.itemView.setOnClickListener {
            onItemClick.onClickItemHome(listHome[position].id)
        }
    }


    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val icon = item.iconMenu
        val title = item.txtMenu_itemHome

        fun bind(item : HomeData){
            icon.setImageDrawable(item.icon)
            title.text = item.name
        }
    }

    interface IOnClickItemHome{
        fun onClickItemHome(id: Int)
    }
}