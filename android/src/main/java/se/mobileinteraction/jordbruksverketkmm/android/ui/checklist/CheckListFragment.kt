package se.mobileinteraction.jordbruksverketkmm.android.ui.checklist

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.mobileinteraction.jordbruksverketkmm.CheckList
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCheckListBinding


class CheckListFragment : Fragment() {


   private var fragmentCheckListBinding: FragmentCheckListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check_list, container, false)
        val binding = FragmentCheckListBinding.bind(view)
        val checkList = CheckList("Grundförbättringar")
        fragmentCheckListBinding = binding

        binding.testLabel.text = this.context?.let { getStringByIdName(it, checkList.title) }
        binding.testText.text = this.context?.let { getStringByIdName(it, checkList.text) }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCheckListBinding = null
    }

    fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.getResources()
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()))
    }
}