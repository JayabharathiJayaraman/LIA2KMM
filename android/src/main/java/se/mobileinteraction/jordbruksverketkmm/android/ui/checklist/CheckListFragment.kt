package se.mobileinteraction.jordbruksverketkmm.android.ui.checklist

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
import se.mobileinteraction.jordbruksverketkmm.CheckList
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCheckListBinding


class CheckListFragment : Fragment() {


   private var fragmentCheckListBinding: FragmentCheckListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var adapterActive: RecyclerView.Adapter<CheckListActiveAdapter.ViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check_list, container, false)
        val binding = FragmentCheckListBinding.bind(view)
        val receivedCategory = arguments?.getString("amount")?: "dummy"
        val checkList = CheckList(receivedCategory)
        fragmentCheckListBinding = binding

        recyclerView = binding.checkListActiveRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapterActive = CheckListActiveAdapter(checkList)
        recyclerView.adapter = adapterActive
        recyclerView.adapter!!.notifyDataSetChanged()

        binding.tmpBack.setOnClickListener {
            view.findNavController().navigateUp()
        }

        binding.testLabel.text = this.context?.let { getStringByIdName(it, checkList.title) }
        binding.testText.text = this.context?.let { getStringByIdName(it, checkList.text) }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCheckListBinding = null
    }

    private fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.getResources()
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()))
    }
}