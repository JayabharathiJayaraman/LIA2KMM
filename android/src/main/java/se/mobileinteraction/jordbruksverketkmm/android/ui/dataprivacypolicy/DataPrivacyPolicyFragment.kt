package se.mobileinteraction.jordbruksverketkmm.android


import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.CheckList
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentDataPrivacyPolicyBinding

class DataPrivacyPolicyFragment : Fragment() {

    private var fragmentDataPrivacyPolicyBinding: FragmentDataPrivacyPolicyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data_privacy_policy, container, false)

        val binding = FragmentDataPrivacyPolicyBinding.bind(view)
        fragmentDataPrivacyPolicyBinding = binding

        val checkList = CheckList("UndvikEllerMinimera")

        //binding.appInfoHeader.text = this.context?.let { getStringByIdName(it, checkList.text) }


        binding.closeLink.setOnClickListener {
            view.findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDataPrivacyPolicyBinding = null
    }

    fun getStringByIdName(context: Context, idName: String?): String? {
        val res: Resources = context.getResources()
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()))
    }
}