package edu.miu.cvbuilderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.miu.cvbuilderapp.databinding.FragmentAboutMeBinding
import edu.miu.cvbuilderapp.model.Curriculum


class AboutMeFragment(private val curriculum: Curriculum?) : Fragment() {
    private lateinit var binding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutMeBinding.inflate(layoutInflater)

        val careerFm : SimpleContentFragment = childFragmentManager.findFragmentById(R.id.aboutMe_fm) as SimpleContentFragment
        careerFm.setTitle("About Me")
        curriculum?.aboutMe?.let { careerFm.setContent(it) }

        return binding.root
    }
}