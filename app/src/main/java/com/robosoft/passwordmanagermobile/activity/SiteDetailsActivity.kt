package com.robosoft.passwordmanagermobile.activity

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.robosoft.passwordmanagermobile.R
import com.robosoft.passwordmanagermobile.databinding.ActivitySiteDetailsBinding
class SiteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiteDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val backButonSite = binding.include.backIv
//        backButonSite.setOnClickListener {
//            onBackPressed()
//        }
//
//        val backButonEdit = binding.customToolbar
//        backButonEdit.setOnClickListener {
//            onBackPressed()
//        }
//
//        binding.passwordToggleIb.setOnClickListener {
//            if (binding.sitePasswordEt.inputType == InputType.TYPE_CLASS_NUMBER) {
//                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_off_24)
//                binding.sitePasswordEt.inputType =
//                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
//            } else {
//                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_24)
//                binding.sitePasswordEt.inputType = InputType.TYPE_CLASS_NUMBER
//            }
//        }
//
//        val editToolbar = binding.customToolbar
//        val siteToolbar = binding.include.root
//        val edit: TextView = binding.include.editTv
//
//
//        edit.setOnClickListener {
//            editToolbar.visibility = View.VISIBLE
//            siteToolbar.visibility = View.INVISIBLE
//
////            val intent = intent
////            val siteUrl = intent.getStringExtra("siteUrl")
////            val siteName = intent.getStringExtra("siteName")
////            val sector = intent.getStringExtra("sector")
////            val userName = intent.getStringExtra("userName")
////            val userPassword = intent.getStringExtra("userPassword")
////            val notes = intent.getStringExtra("notes")
////
////            binding.siteNameEt.setText(siteName)
////            binding.urlEt.setText(siteUrl)
////            binding.folderEt.setText(sector)
////            binding.userNameEt.setText(userName)
////            binding.sitePasswordEt.setText(userPassword)
////            binding.notesEt.setText(notes)
//        }

//        val intent = intent
//        val siteUrl = intent.getStringExtra("siteUrl")
//        val siteName = intent.getStringExtra("siteName")
//        val sector = intent.getStringExtra("sector")
//        val userName = intent.getStringExtra("userName")
//        val userPassword = intent.getStringExtra("userPassword")
//        val notes = intent.getStringExtra("notes")
//
//        binding.siteNameEt.text = siteName
//        binding.urlEt.text = siteUrl
//        binding.folderEt.text = sector
//        binding.userNameEt.text = userName
//        binding.sitePasswordEt.setText(userPassword)
//        binding.notesEt.text = notes
    }
}

