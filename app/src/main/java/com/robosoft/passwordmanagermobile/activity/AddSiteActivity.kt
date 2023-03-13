package com.robosoft.passwordmanagermobile.activity

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.robosoft.passwordmanagermobile.R
import com.robosoft.passwordmanagermobile.database.AppDatabase
import com.robosoft.passwordmanagermobile.databinding.ActivityAddSiteBinding
import com.robosoft.passwordmanagermobile.entity.SiteDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddSiteActivity : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {

    private lateinit var database: AppDatabase
    private lateinit var binding: ActivityAddSiteBinding
    var sectorSelected = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddSiteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordToggleIb.setOnClickListener {
            if (binding.sitePasswordEt.inputType == InputType.TYPE_CLASS_NUMBER) {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                binding.sitePasswordEt.inputType =
                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            } else {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_24)
                binding.sitePasswordEt.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }

        val backButon = binding.customToolbar.getChildAt(1)
        backButon.setOnClickListener {
            onBackPressed()
        }
        val intent = intent
        val phoneNo = intent.getStringExtra("phoneNo")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.spinner_array)
        )

        binding.sectorFolderSpinner.adapter = arrayAdapter
        binding.sectorFolderSpinner.onItemSelectedListener = this

        var siteUrl = binding.urlEt.text
        var siteName = binding.siteNameEt.text
        var userName = binding.userNameEt.text
        var userPassword = binding.sitePasswordEt.text
        var userNotes = binding.notesEt.text


        binding.saveBt.setOnClickListener {
            insertSiteDetails(
                phoneNo.toString(),
                siteUrl.toString(),
                siteName.toString(),
                sectorSelected,
                userName.toString(),
                userPassword.toString(),
                userNotes.toString()
            )
            onBackPressed()
            Toast.makeText(binding.root.context,"Saved Successfully",Toast.LENGTH_LONG).show()
        }

        binding.resetBt.setOnClickListener {
            binding.urlEt.text = null
            binding.siteNameEt.text = null
            binding.userNameEt.text = null
            binding.sitePasswordEt.text = null
            binding.notesEt.text = null

        }

    }

    fun insertSiteDetails(
        phoneNo: String,
        siteUrl: String,
        siteName: String,
        sector: String,
        username: String,
        userPassword: String,
        userNotes: String
    ) {
        database = AppDatabase.getDatabase((applicationContext ?: this))

        GlobalScope.launch {
            database.userDetailsDao().insertSite(
                SiteDetails(
                    null,
                    phoneNo,
                    siteUrl,
                    siteName,
                    sector,
                    username,
                    userPassword,
                    username
                )
            )
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        sectorSelected = resources.getStringArray(R.array.spinner_array)[p2].toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
