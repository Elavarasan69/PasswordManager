package com.robosoft.passwordmanagermobile.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.robosoft.passwordmanagermobile.Communicator
import com.robosoft.passwordmanagermobile.adapter.ViewPagerAdapter
import com.robosoft.passwordmanagermobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Communicator {

    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> "SIGN IN"
                1 -> "SIGN UP"
                else -> throw Resources.NotFoundException("Position Not Found")
            }
        }.attach()
    }
    override fun passControl() {
        binding.viewPager.setCurrentItem(0,true)
        Toast.makeText(binding.root.context,"Congrtats!!! Success\n" +
                "Signin to access the vault", Toast.LENGTH_LONG).show()
    }
    override fun passController() {
        startActivity(Intent(this,HomeActivity::class.java))
    }
}
