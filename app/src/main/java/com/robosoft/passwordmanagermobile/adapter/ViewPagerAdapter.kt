package com.robosoft.passwordmanagermobile.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robosoft.passwordmanagermobile.fragment.SignInFragment
import com.robosoft.passwordmanagermobile.fragment.SignUpFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> SignInFragment()
            1 -> SignUpFragment()
            else -> throw Resources.NotFoundException("Position Not Found")
        }
    }
}
