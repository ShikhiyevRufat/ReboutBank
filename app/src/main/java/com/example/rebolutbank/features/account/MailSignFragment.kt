package com.example.rebolutbank.features.account

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.nextmailsign.setOnClickListener {
            valid_email()
        }

        return binding.root
    }



    private fun valid_email() {

        val emailText = binding.email.text.toString()


        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches() || emailText.isNullOrEmpty()) {
            binding.emaillayout.helperText = "Email is not true"
        }
        else{
            val action = MailSignFragmentDirections.actionMailSignFragmentToPassCodeFragment()
            findNavController().navigate(action)
        }

    }



}