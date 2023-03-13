package com.robosoft.passwordmanagermobile.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.passwordmanagermobile.adapter.RecyclerAdapter
import com.robosoft.passwordmanagermobile.database.AppDatabase
import com.robosoft.passwordmanagermobile.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val relativeLayout = binding.textLayout
        val searchBar = binding.searchLayout
        val search: ImageView = binding.include.searchIv

        search.setOnClickListener {
            if (relativeLayout.visibility == View.VISIBLE) {
                relativeLayout.visibility = View.INVISIBLE
                searchBar.visibility = View.VISIBLE
            }
            else{
                relativeLayout.visibility = View.VISIBLE
                searchBar.visibility = View.INVISIBLE
            }
        }

        binding.include.profileIv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        database = AppDatabase.getDatabase((applicationContext ?: this))
        binding.addIb.setOnClickListener {
            val intent = Intent(this, AddSiteActivity::class.java)
            startActivity(intent)
        }

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this)

        val siteData = database.userDetailsDao().getAllSite()
        siteData.observe(this@HomeActivity, {
            binding.homeRecyclerView.adapter = RecyclerAdapter(this, it)
            binding.countTv.text = it.size.toString() })
    }
}
