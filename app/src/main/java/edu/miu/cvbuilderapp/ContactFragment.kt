package edu.miu.cvbuilderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.miu.cvbuilderapp.adapter.ContactListAdapter
import edu.miu.cvbuilderapp.databinding.FragmentContactBinding
import edu.miu.cvbuilderapp.model.Contact
import edu.miu.cvbuilderapp.model.Curriculum

class ContactFragment(private val curriculum: Curriculum?) : Fragment() {
    private lateinit var binding : FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(layoutInflater)

        binding.contactFrmtContactLv.adapter = this.context?.let { ContactListAdapter(it,
            curriculum?.contacts as ArrayList<Contact>
        ) }

        return binding.root
    }
}