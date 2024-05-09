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
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignOrLoginFragment : Fragment() {

    private lateinit var binding: FragmentSignOrLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
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
        binding.googlelogin.setOnClickListener {

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

    private lateinit var signInRequest : BeginSignInRequest
    private fun googlelogin(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.google_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

        val signInIntent = googleSignInClient.signInIntent
        

    }

    private fun register(){
        val action = SignOrLoginFragmentDirections.actionSignOrLoginFragmentToSignUpFragment()
        findNavController().navigate(action)
    }

}