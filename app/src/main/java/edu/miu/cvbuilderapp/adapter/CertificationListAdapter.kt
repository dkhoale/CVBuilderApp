package edu.miu.cvbuilderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvbuilderapp.databinding.CertificationItemViewBinding
import edu.miu.cvbuilderapp.model.Certification

class CertificationListAdapter(val context: Context, private val certifications: ArrayList<Certification>) : RecyclerView.Adapter<CertificationListAdapter.CertificationListVH>(){
    private lateinit var binding: CertificationItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationListVH {
        binding = CertificationItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CertificationListVH(binding)
    }

    override fun onBindViewHolder(holder: CertificationListVH, position: Int) {
        holder.binding.certItemImageView.setImageResource(certifications[position].icon)
        holder.binding.certItemNameTv.text = certifications[position].name
    }

    override fun getItemCount(): Int {
        return this.certifications.size
    }

    inner class CertificationListVH(val binding: CertificationItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}