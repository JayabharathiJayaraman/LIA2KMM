package se.mobileinteraction.jordbruksverketkmm.android.ui.checklist

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.checklists.ChecklistViewModel
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem

class CheckListInActiveAdapter(val viewModel: ChecklistViewModel) :
    RecyclerView.Adapter<CheckListInActiveAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckListInActiveAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_checklist_active, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CheckListInActiveAdapter.ViewHolder, position: Int) {
        val listToShow = mutableListOf<ChecklistItem>()
        for (elem in viewModel.state.value.checklist.itemList) {
            if (!elem.active) {
                listToShow.add(elem)
            }
        }
        holder.itemLabel.text = context?.let {
            getStringByIdName(
                it,
                listToShow[position].title
            )
        }
        holder.itemText.text = context?.let { getStringByIdName(it, listToShow[position].text) }
        holder.itemAdd.setBackgroundResource(R.drawable.plus_small)
        holder.id = listToShow[position].id

        if (viewModel.checklist.id == Checklist.Category.UNDVIKELLERMINIMERA) {
            holder.itemAdd.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        var inActives = 0
        for (elem in viewModel.state.value.checklist.itemList) {
            if (!elem.active) {
                inActives++
            }
        }
        Log.d("list size", inActives.toString())
        return inActives
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemLabel: TextView = itemView.findViewById(R.id.checklist_active_item_label)
        var itemText: TextView = itemView.findViewById(R.id.checklist_active_item_text)
        var itemAdd: ImageButton = itemView.findViewById(R.id.checkList_item_add)
        var itemSwitch: Switch = itemView.findViewById(R.id.checklist_active_item_switch)
        lateinit var id: String

        init {
            itemAdd.setOnClickListener {
                viewModel.triggerStateActive(id)
            }
        }
    }

    private fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.resources
        return res.getString(res.getIdentifier(idName, "string", context.packageName))
    }
}