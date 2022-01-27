package com.example.ccl3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val fragments: List<Fragment>) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
//        return when(position){
//            0->{TabTaskFragment()}
//            1->{TabInsightFragment()}
//            else->{Fragment()}
//        }
    }

}