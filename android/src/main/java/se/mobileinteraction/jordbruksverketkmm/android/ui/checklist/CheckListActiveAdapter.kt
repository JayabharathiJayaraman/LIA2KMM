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

class CheckListActiveAdapter(checkList: Checklist): RecyclerView.Adapter<CheckListActiveAdapter.ViewHolder>() {
    private var _checkList = checkList
    private var context: Context?= null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckListActiveAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_checklist_active, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CheckListActiveAdapter.ViewHolder, position: Int) {
        holder.itemLabel.text = context?.let { getStringByIdName(it, _checkList.itemList.filter { it.active }[position].title) }
        holder.itemText.text = context?.let { getStringByIdName(it, _checkList.itemList.filter { it.active }[position].text) }
        holder.itemAdd.setOnClickListener {
            val tmpName = _checkList.itemList.filter { it.active }[position].title
            Log.d("!!!",_checkList.itemList.filter { it.title == tmpName }[0].active.toString())
            //_checkList.itemList.filter { it.active }[position].switchActive()
            Log.d("!!!",_checkList.itemList.filter { it.title == tmpName }[0].active.toString())
        }
        if(_checkList.id == "UndvikEllerMinimera"){
            holder.itemAdd.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
       return _checkList.itemList.filter { it.active }.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemLabel: TextView = itemView.findViewById(R.id.checklist_active_item_label)
        var itemText: TextView = itemView.findViewById(R.id.checklist_active_item_text)
        var itemAdd: ImageButton = itemView.findViewById(R.id.checkList_item_add)
        var itemSwitch: Switch = itemView.findViewById(R.id.checklist_active_item_switch)
        }

    private fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.resources
        return res.getString(res.getIdentifier(idName, "string", context.packageName))
    }
}