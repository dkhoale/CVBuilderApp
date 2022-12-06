package edu.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import edu.miu.cvbuilderapp.model.Curriculum

class MainPageAdapter(fragmentActivity:FragmentActivity, private val curriculum: Curriculum) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4 // We have 4 fragments

    // Provide a new Fragment associated with the specified position.
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment(curriculum)
            1 -> AboutMeFragment()
            2 -> WorkFragment()
            3 -> ContactFragment()
            else -> Fragment()
        }
    }
}