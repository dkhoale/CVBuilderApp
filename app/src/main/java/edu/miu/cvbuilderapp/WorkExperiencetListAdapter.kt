package edu.miu.cvbuilderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvbuilderapp.databinding.WorkItemViewBinding
import edu.miu.cvbuilderapp.model.WorkExperience
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class WorkExperiencetListAdapter(val context: Context, private val workExps: ArrayList<WorkExperience>) : RecyclerView.Adapter<WorkExperiencetListAdapter.WorkExperienceListVH>(){
    private lateinit var binding: WorkItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkExperienceListVH {
        binding = WorkItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkExperienceListVH(binding)
    }

    override fun onBindViewHolder(holder: WorkExperienceListVH, position: Int) {
        holder.binding.workExpItemImageView.setImageResource(workExps[position].company.icon)
        holder.binding.workExpItemTvPosition.text = workExps[position].position
        holder.binding.workExpItemTvCompany.text = workExps[position].company.name
        holder.binding.workExpItemTvPeriod.text = workExps[position].from.format(
            DateTimeFormatter.ofPattern("MMM yyyy"))
        holder.binding.workExpItemTvLocation.text = workExps[position].location
        holder.binding.workExpItemTvDescription.text = workExps[position].description

    }

    override fun getItemCount(): Int {
        return this.workExps.size
    }

    inner class WorkExperienceListVH(val binding: WorkItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}