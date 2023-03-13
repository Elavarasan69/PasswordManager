package com.robosoft.passwordmanagermobile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(supportManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(supportManager, lifecycle) {
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragments(fragments: List<Fragment>) {
        fragmentList.addAll(fragments)
    }
}