package edu.miu.cvbuilderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvbuilderapp.databinding.EducationItemViewBinding
import edu.miu.cvbuilderapp.model.Education

class EducationListAdapter(val context: Context, private val educations: ArrayList<Education>) : RecyclerView.Adapter<EducationListAdapter.EducationListVH>(){
    private lateinit var binding: EducationItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationListVH {
        binding = EducationItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationListVH(binding)
    }

    override fun onBindViewHolder(holder: EducationListVH, position: Int) {
        holder.binding.educationItemImageView.setImageResource(educations[position].icon)
        holder.binding.educationItemCollegeTv.text = educations[position].collegeName
        holder.binding.educationItemMajorTv.text = educations[position].degree
    }

    override fun getItemCount(): Int {
        return this.educations.size
    }

    inner class EducationListVH(val binding: EducationItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}