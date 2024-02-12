package com.example.rebolutbank.features.account

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentMailSignBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MailSignFragment : Fragment() {

    private lateinit var binding : FragmentMailSignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMailSignBinding.inflate(inflater)

        emailActivate()
        valid_password()

        binding.nextpasscode.setOnClickListener {
            register()
        }

        return binding.root
    }



    private fun emailActivate(){
        binding.email.setOnFocusChangeListener{_,focused->
            if(!focused){
                binding.emaillayout.helperText = valid_email()

            }
        }

        binding.password.setOnFocusChangeListener{_, focused->
            if(!focused){
                binding.passwordlayout.helperText = valid_password()
            }
        }
    }

    private fun valid_password() : String? {

        val passwordtext = binding.password.text.toString()

        if (passwordtext.length < 8){
            return "Minimum character 8"
        }
        else if (!passwordtext.matches(".*[@#$%^&*!]*.".toRegex()) ){
            return "Minimum (@#\$%^&*!.,?) one character "
        }
        return null

    }

    private fun valid_email() : String? {

        val emailText = binding.email.text.toString()


        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email"
        }

        return null
    }

    fun register() {
        val firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(
            binding.email.text.toString(),
            binding.password.text.toString())
            .addOnSuccessListener {
                val action = MailSignFragmentDirections.actionMailSignFragmentToPassCodeFragment()
                findNavController().navigate(action)
            }.addOnFailureListener {
                (it as? FirebaseAuthException)?.errorCode?.let { errorCode->
                    Toast.makeText(context,"Account not been successful!",Toast.LENGTH_SHORT).show()
                }

            }
    }



}