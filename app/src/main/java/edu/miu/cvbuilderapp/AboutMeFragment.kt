package edu.miu.cvbuilderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.miu.cvbuilderapp.databinding.FragmentAboutMeBinding
import edu.miu.cvbuilderapp.model.Certification
import edu.miu.cvbuilderapp.model.Curriculum
import edu.miu.cvbuilderapp.model.Education


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

        val educationFm = childFragmentManager.findFragmentById(R.id.education_list_fm) as EducationListFragment
        educationFm.educations = curriculum?.educations as ArrayList<Education>

        val certificationFm = childFragmentManager.findFragmentById(R.id.certificate_list_fm) as CertificationListFragment
        certificationFm.certificates = curriculum.certifications as ArrayList<Certification>

        return binding.root
    }
}