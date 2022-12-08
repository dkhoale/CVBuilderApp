package edu.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.miu.cvbuilderapp.databinding.FragmentEducationListBinding
import edu.miu.cvbuilderapp.model.Education

class EducationListFragment : Fragment() {
    lateinit var binding: FragmentEducationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationListBinding.inflate(layoutInflater)
        val educations = arrayListOf<Education>(
            Education("Harvard University","Master of Science in Electricity", R.drawable.harvard),
            Education("Maharishi University","Master of Science in Computer Science", R.drawable.maharishi))

        binding.eduFrmtEducationLv.adapter = this.context?.let { EducationListAdapter(it,educations) }
        return binding.root
    }

}