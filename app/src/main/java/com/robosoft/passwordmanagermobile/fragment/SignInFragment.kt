package com.robosoft.passwordmanagermobile.fragment

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.robosoft.passwordmanagermobile.Communicator
import com.robosoft.passwordmanagermobile.R
import com.robosoft.passwordmanagermobile.database.AppDatabase
import com.robosoft.passwordmanagermobile.databinding.FragmentSignInBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var database: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.passwordToggleIb.setOnClickListener {
            if (binding.mPinEt.inputType == InputType.TYPE_CLASS_NUMBER) {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                binding.mPinEt.inputType =
                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            } else {
                binding.passwordToggleIb.setImageResource(R.drawable.ic_baseline_visibility_24)
                binding.mPinEt.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }



        binding.submitBt.setOnClickListener {
            val phoneNumber = binding.mobileNumberEt.text
            val mPin = binding.mPinEt.text
            if (phoneNumber.isEmpty() && mPin.isEmpty()) {
                Toast.makeText(
                    activity?.applicationContext, "Please fill all details",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if (!validate_phone(phoneNumber.toString())){
                binding.mobileNumberEt.error = "Please enter valid mobile number"
            }
            else if (mPin.length != 4 ){
                binding.mPinEt.error = "Please enter 4 digit MPin"
            }
            else {
                GlobalScope.launch(Dispatchers.IO) {
                    database = AppDatabase.getDatabase(binding.root.context)
                    val userDetails = database.userDetailsDao().getAll()
                    val size = userDetails.size

                    for (index in 0 until size ) {
                        if ((phoneNumber.toString() == userDetails[index].mobileNumber) && (mPin.toString() == userDetails[index].mPin)) {
                                val communicator = activity as Communicator
                                communicator.passController()
                                    break
                            } else Snackbar.make(binding.root,"Mobile number or MPin is incorrect",Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        return binding.root
    }

    private fun validate_phone(phoneNumber: String): Boolean {
        val pattern = Pattern.compile("[6-9]{1}[0-9]{9}")
        var matcher = pattern.matcher(phoneNumber)
        return matcher.matches()
    }
}
