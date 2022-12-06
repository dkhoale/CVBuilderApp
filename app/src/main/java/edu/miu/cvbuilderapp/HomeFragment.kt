package edu.miu.cvbuilderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.miu.cvbuilderapp.databinding.FragmentHomeBinding
import edu.miu.cvbuilderapp.model.Curriculum


class HomeFragment(private val curriculum: Curriculum?) : Fragment() {
private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val careerFm : SimpleContentFragment = childFragmentManager.findFragmentById(R.id.career_fm) as SimpleContentFragment
        careerFm.setTitle("Career Note")
        curriculum?.careerNote?.let { careerFm.setContent(it) }

        val coreCompetencyFm : SimpleContentFragment = childFragmentManager.findFragmentById(R.id.core_competency_fm) as SimpleContentFragment
        coreCompetencyFm.setTitle("Core Competency")
        curriculum?.coreCompetency?.let { coreCompetencyFm.setContent(it) }

        return binding.root
    }

}