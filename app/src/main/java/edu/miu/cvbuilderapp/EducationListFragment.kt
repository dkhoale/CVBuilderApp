package edu.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.miu.cvbuilderapp.adapter.EducationListAdapter
import edu.miu.cvbuilderapp.databinding.FragmentEducationListBinding
import edu.miu.cvbuilderapp.model.Education

class EducationListFragment : Fragment() {
    lateinit var binding: FragmentEducationListBinding
    var educations = ArrayList<Education>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationListBinding.inflate(layoutInflater)

        binding.eduFrmtEducationLv.adapter = this.context?.let { EducationListAdapter(it,educations) }
        return binding.root
    }

}