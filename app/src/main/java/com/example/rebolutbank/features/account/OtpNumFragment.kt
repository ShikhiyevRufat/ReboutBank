package com.example.rebolutbank.features.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentOtpNumBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpNumFragment : Fragment() {

    private var storeVerificationId: String? = ""
    private lateinit var auth : FirebaseAuth

    private lateinit var binding: FragmentOtpNumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpNumBinding.inflate(inflater)

        auth = Firebase.auth

        storeVerificationId = arguments?.getString("storedVerificationId")

        binding.nextotp.setOnClickListener {
            val verId = mutableListOf<String>(
                binding.opt1.text.toString(),
                binding.opt2.text.toString(),
                binding.opt3.text.toString(),
                binding.opt4.text.toString(),
                binding.opt5.text.toString(),
                binding.opt6.text.toString(),
            ).toString()
            verifyPhoneNumberWithCode(storeVerificationId,verId)
        }

        return binding.root
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Succes", "signInWithCredential:success")

                    val user = task.result?.user

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("Failed", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
    // [END sign_in_with_phone]



}