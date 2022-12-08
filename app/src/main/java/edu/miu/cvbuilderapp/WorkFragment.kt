package edu.miu.cvbuilderapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import edu.miu.cvbuilderapp.databinding.FragmentWorkBinding
import edu.miu.cvbuilderapp.model.CompanyMapping
import edu.miu.cvbuilderapp.model.WorkExperience
import java.time.LocalDate

class WorkFragment : Fragment() {
    private lateinit var binding: FragmentWorkBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkBinding.inflate(layoutInflater)
        val workEsps = arrayListOf<WorkExperience>(
            WorkExperience("Software Developer",CompanyMapping.VISA,
                LocalDate.of(2020,2,1),LocalDate.of(2022,9,1),"Texas, US", "Lead dev team to develop commercehub.")
        )

        binding.rcv.adapter = this.context?.let { WorkExperiencetListAdapter(it,workEsps) }

        binding.fab.setOnClickListener{
            val dialog = AddWorkExperienceDialog()
            parentFragmentManager.setFragmentResultListener("OK",viewLifecycleOwner,
                FragmentResultListener {requestKey, result ->
                    when(requestKey){
                        "OK" -> {
                            workEsps.add(result.getSerializable("workExperience") as WorkExperience)
                            binding.rcv.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            )
            dialog.show(parentFragmentManager, "addExpDialog")
        }

        return binding.root
    }

}