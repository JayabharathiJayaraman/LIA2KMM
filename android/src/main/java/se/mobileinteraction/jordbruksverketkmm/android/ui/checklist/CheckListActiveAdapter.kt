package se.mobileinteraction.jordbruksverketkmm.android.ui.checklist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.mobileinteraction.jordbruksverketkmm.CheckList
import se.mobileinteraction.jordbruksverketkmm.android.R

class CheckListActiveAdapter: RecyclerView.Adapter<CheckListActiveAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckListActiveAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_checklist_active, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CheckListActiveAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){



        }

}