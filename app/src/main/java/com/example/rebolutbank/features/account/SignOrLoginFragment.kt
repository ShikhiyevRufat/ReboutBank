package com.example.rebolutbank.features.account

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.MainActivity
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

        if (binding.loginemail.text.toString().isBlank() || binding.loginpassword.text.toString().isBlank()) {
            return
        }

        val firebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(
            binding.loginemail.text.toString(),
            binding.loginpassword.text.toString())
            .addOnSuccessListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
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