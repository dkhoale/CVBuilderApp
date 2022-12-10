package edu.miu.cvbuilderapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import edu.miu.cvbuilderapp.databinding.FragmentWorkBinding
import edu.miu.cvbuilderapp.model.Curriculum
import edu.miu.cvbuilderapp.model.User
import edu.miu.cvbuilderapp.model.WorkExperience
import edu.miu.cvbuilderapp.util.GsonUtil

class WorkFragment(private val curriculum: Curriculum?,
    private val currentLogin: User
) : Fragment() {
    private lateinit var binding: FragmentWorkBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkBinding.inflate(layoutInflater)
        val workExps = curriculum?.workExperiences as ArrayList<WorkExperience>

        binding.rcv.adapter = this.context?.let { WorkExperiencetListAdapter(it, workExps) }

        binding.fab.setOnClickListener{
            val dialog = AddWorkExperienceDialog()
            parentFragmentManager.setFragmentResultListener("OK",viewLifecycleOwner,
                FragmentResultListener {requestKey, result ->
                    when(requestKey){
                        "OK" -> {
                            workExps.add(result.getSerializable("workExperience") as WorkExperience)
                            binding.rcv.adapter?.notifyDataSetChanged()

                            curriculum.workExperiences = workExps
                            saveToSharedPreferences(curriculum)
                        }
                    }
                }
            )
            dialog.show(parentFragmentManager, "addExpDialog")
        }

        return binding.root
    }

    private fun saveToSharedPreferences(curriculum: Curriculum) {
        val spf = this.context?.getSharedPreferences("curriculums", AppCompatActivity.MODE_PRIVATE)
        val curriculumJson = spf?.getString("curriculums","")
        val curriculums = GsonUtil.fromJson<HashMap<String, Curriculum>>(
            curriculumJson!!)
        curriculums[currentLogin.email] = curriculum
        val curJson = GsonUtil.toJson(curriculums)
        val spfe = spf.edit()
        spfe.putString("curriculums",curJson)
        spfe.apply()
    }

}