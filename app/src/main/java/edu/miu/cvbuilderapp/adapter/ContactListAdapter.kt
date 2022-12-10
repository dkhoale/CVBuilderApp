package edu.miu.cvbuilderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvbuilderapp.databinding.ContactItemViewBinding
import edu.miu.cvbuilderapp.model.Contact

class ContactListAdapter(val context: Context, private val contacts: ArrayList<Contact>) : RecyclerView.Adapter<ContactListAdapter.ContactListVH>(){
    private lateinit var binding: ContactItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListVH {
        binding = ContactItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactListVH(binding)
    }

    override fun onBindViewHolder(holder: ContactListVH, position: Int) {
        holder.binding.contactItemImageView.setImageResource(contacts[position].icon)
        holder.binding.contactItemTitleTv.text = contacts[position].content
        holder.binding.contactItemDescTv.text = contacts[position].type
    }

    override fun getItemCount(): Int {
        return this.contacts.size
    }

    inner class ContactListVH(val binding: ContactItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}