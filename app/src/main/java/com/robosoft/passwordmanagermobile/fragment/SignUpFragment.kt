package com.robosoft.passwordmanagermobile.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.robosoft.passwordmanagermobile.Communicator
import com.robosoft.passwordmanagermobile.R
import com.robosoft.passwordmanagermobile.database.AppDatabase
import com.robosoft.passwordmanagermobile.databinding.FragmentSignUpBinding
import com.robosoft.passwordmanagermobile.entity.UserDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpFragment : Fragment() {
    private lateinit var database: AppDatabase
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        database = AppDatabase.getDatabase(binding.root.context)

        binding.passwordToggleIb.setOnClickListener {
            if (binding.checkMPinEt.inputType == InputType.TYPE_CLASS_NUMBER) {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                binding.checkMPinEt.inputType =
                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            } else {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_24)
                binding.checkMPinEt.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }

        binding.submitBt.setOnClickListener {
            writeData()
            binding.mobileNumberEt.text.clear()
            binding.setMPinEt.text.clear()
            binding.checkMPinEt.text.clear()
        }

        return binding.root
    }

    private fun writeData() {

        val phoneNumber = binding.mobileNumberEt.text
        val setMPin = binding.setMPinEt.text
        val checkMPin = binding.checkMPinEt.text

        if (phoneNumber.isEmpty() && setMPin.isEmpty() && checkMPin.isEmpty()) {
            Toast.makeText(
                activity?.applicationContext, "Please fill all details",
                Toast.LENGTH_LONG
            ).show()
        } else if (!validate_phone(phoneNumber.toString())){
            binding.mobileNumberEt.error = "Please enter valid mobile number"
        }
        else if (setMPin.length != 4 ){
            binding.setMPinEt.error = "Please enter 4 digit MPin"
        }
        else if (checkMPin.length != 4 ){
            binding.checkMPinEt.error = "Please enter 4 digit MPin"
        }
        else if (setMPin.toString() != checkMPin.toString()) {
            Toast.makeText(
                activity?.applicationContext, "MPin does not match",
                Toast.LENGTH_LONG
            ).show()
        } else {
            GlobalScope.launch {
                val user = UserDetails(null, phoneNumber.toString(), setMPin.toString())
                database.userDetailsDao().insert(user)
            }
            val communicator = activity as Communicator
            communicator.passControl()
        }
    }
    private fun validate_phone(phoneNumber: String): Boolean {
        val pattern = Pattern.compile("[6-9]{1}[0-9]{9}")
        var matcher = pattern.matcher(phoneNumber)
        return matcher.matches()
    }
}


