package com.example.rebolutbank.features.account

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentSignOrLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignOrLoginFragment : Fragment() {

    private lateinit var binding: FragmentSignOrLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignOrLoginBinding.inflate(inflater)
        binding.loginbutton.setOnClickListener {
            login()
        }
        binding.registerbutton.setOnClickListener {
            register()
        }
        return binding.root
    }

    private fun login(){
        val firebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(
            binding.loginemail.text.toString(),
            binding.loginpassword.text.toString())
            .addOnSuccessListener {
                val action = SignOrLoginFragmentDirections.actionSignOrLoginFragmentToMainMenuFragment()
                findNavController().navigate(action)
            }
            .addOnFailureListener {
                binding.signuptocontinue.text = "It is a false account!"
                binding.signuptocontinue.setTextColor(Color.RED)
            }
    }

    private fun register(){
        val action = SignOrLoginFragmentDirections.actionSignOrLoginFragmentToSignUpFragment()
        findNavController().navigate(action)
    }

}