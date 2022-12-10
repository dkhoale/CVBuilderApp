package edu.miu.cvbuilderapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.miu.cvbuilderapp.databinding.FragmentHomeBinding
import edu.miu.cvbuilderapp.model.Curriculum
import edu.miu.cvbuilderapp.model.User
import java.util.*


class HomeFragment(private val curriculum: Curriculum?, private val currentLogin: User) : Fragment() {
private lateinit var binding : FragmentHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val careerFm : SimpleContentFragment = childFragmentManager.findFragmentById(R.id.career_fm) as SimpleContentFragment
        careerFm.setTitle(getString(R.string.career_note_title))
        curriculum?.careerNote?.let { careerFm.setContent(it) }

        val coreCompetencyFm : SimpleContentFragment = childFragmentManager.findFragmentById(R.id.core_competency_fm) as SimpleContentFragment
        coreCompetencyFm.setTitle(getString(R.string.core_competency_title))
        curriculum?.coreCompetency?.let { coreCompetencyFm.setContent(it) }

        binding.homeFrmtNameTv.text = "${currentLogin.firstName.uppercase()} ${currentLogin.lastName.uppercase()}"
        binding.homeFrmtProfileImage.setImageResource(currentLogin.avatar)

        return binding.root
    }

}