package se.mobileinteraction.jordbruksverketkmm.android.ui.checklist

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCheckListBinding

class CheckListFragment : Fragment() {

   private var fragmentCheckListBinding: FragmentCheckListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var adapterActive: RecyclerView.Adapter<CheckListActiveAdapter.ViewHolder>? = null
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check_list, container, false)
        val binding = FragmentCheckListBinding.bind(view)
        val receivedCategory = arguments?.getString("amount")?: "dummy"
        val checkList = Checklist(receivedCategory)
        fragmentCheckListBinding = binding

        recyclerView = binding.checkListActiveRecyclerView
        recyclerView.layoutManager = object : LinearLayoutManager(activity){
         /*   override fun canScrollVertically(): Boolean {
                return false
            }*/
        }
        adapterActive = CheckListActiveAdapter(checkList)
        recyclerView.adapter = adapterActive
        recyclerView.adapter!!.notifyDataSetChanged()

        binding.tmpBack.setOnClickListener {
            view.findNavController().navigateUp()
        }

        if(receivedCategory == "UndvikEllerMinimera"){
            binding.testLabelEjAktuellaAtgarder.visibility = View.GONE
            binding.testText3.visibility = View.GONE
            binding.testText4.visibility = View.GONE
        }

        binding.testLabel.text = this.context?.let { getStringByIdName(it, checkList.title) }
        binding.testText1.text = this.context?.let { getStringByIdName(it, checkList.text) }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCheckListBinding = null
    }

    private fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.resources
        return res.getString(res.getIdentifier(idName, "string", context.packageName))
    }
}