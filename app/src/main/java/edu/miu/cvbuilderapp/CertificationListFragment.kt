package edu.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.miu.cvbuilderapp.adapter.CertificationListAdapter
import edu.miu.cvbuilderapp.databinding.FragmentCertificationListBinding
import edu.miu.cvbuilderapp.model.Certification


class CertificationListFragment : Fragment() {
    lateinit var binding: FragmentCertificationListBinding
    var certificates = ArrayList<Certification>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCertificationListBinding.inflate(layoutInflater)

        binding.certFrmtCertificatesLv.adapter = this.context?.let { CertificationListAdapter(it,certificates) }

        return binding.root
    }

}