package com.example.rebolutbank.features.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.rebolutbank.MainActivity
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentAcountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcountFragment : Fragment() {
    private lateinit var binding : FragmentAcountBinding
    private val firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcountBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.uid?.let { userId ->
            getUserInfo(userId)
        }
        binding.toolbar.setNavigationOnClickListener {
            removeFragmentB()
        }

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Show bottom navigation bar when AccountFragment is destroyed
        (requireActivity() as MainActivity).showBottomNavigation()
    }

    private fun getUserInfo(userId: String) {
        val userDocument = firestore.collection("USERS").document(userId)
        userDocument.get()
            .addOnSuccessListener { document ->
                val username = document.getString("username")
                val lastnumber = document.getString("lastname")

                binding.toolbar.title = username + lastnumber

            }
            .addOnFailureListener {
                Toast.makeText(context,"Xəta!",Toast.LENGTH_SHORT).show()
            }
    }


    private fun removeFragmentB() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentB = fragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (fragmentB != null) {
            fragmentManager.beginTransaction().remove(fragmentB).commit()
        }
    }
}
