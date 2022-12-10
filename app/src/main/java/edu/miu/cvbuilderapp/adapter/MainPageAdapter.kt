package edu.miu.cvbuilderapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import edu.miu.cvbuilderapp.AboutMeFragment
import edu.miu.cvbuilderapp.ContactFragment
import edu.miu.cvbuilderapp.HomeFragment
import edu.miu.cvbuilderapp.WorkFragment
import edu.miu.cvbuilderapp.model.Curriculum

class MainPageAdapter(fragmentActivity:FragmentActivity,
                      private val curriculum: Curriculum,
                      private val currentLogin: String) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4 // We have 4 fragments

    // Provide a new Fragment associated with the specified position.
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment(curriculum)
            1 -> AboutMeFragment(curriculum)
            2 -> WorkFragment(curriculum,currentLogin)
            3 -> ContactFragment(curriculum)
            else -> Fragment()
        }
    }
}