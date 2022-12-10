package edu.miu.cvbuilderapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.adapter.LocalDateAdapter
import edu.miu.cvbuilderapp.databinding.FragmentWorkBinding
import edu.miu.cvbuilderapp.model.Curriculum
import edu.miu.cvbuilderapp.model.WorkExperience
import java.time.LocalDate

class WorkFragment(private val curriculum: Curriculum?,
    private val currentLogin: String) : Fragment() {
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
        val curriculums = fromJson<HashMap<String, Curriculum>>(
            curriculumJson!!)
        curriculums[currentLogin] = curriculum
        val curJson = toJson(curriculums)
        val spfe = spf.edit()
        spfe.putString("curriculums",curJson)
        spfe.apply()
    }

    inline fun <reified T> fromJson(json: String) = GsonBuilder().registerTypeAdapter(LocalDate::class.java,
        LocalDateAdapter()).create().fromJson<T>(json, object: TypeToken<T>() {}.type)
    fun toJson(objectJson: Any): String = GsonBuilder().registerTypeAdapter(LocalDate::class.java,
        LocalDateAdapter()).create().toJson(objectJson)
}