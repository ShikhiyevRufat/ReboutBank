package com.example.rebolutbank.features.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.databinding.FragmentPassCodeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassCodeFragment : Fragment() {

    private lateinit var binding: FragmentPassCodeBinding
    private val editTexts = arrayOfNulls<EditText>(6)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPassCodeBinding.inflate(inflater)
        val editTextIds = arrayOf(
            binding.passcode1,
            binding.passcode2,
            binding.passcode3,
            binding.passcode4,
            binding.passcode5,
        )

        for (i in editTextIds.indices) {
            editTexts[i] = editTextIds[i]
            setEditTextListener(i, editTextIds[i])
        }

        binding.nextcarddesign.setOnClickListener {
            val action = PassCodeFragmentDirections.actionPassCodeFragmentToCardDesignFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setEditTextListener(index: Int, editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // Move focus to the next EditText if a digit is entered
                    moveFocusToNextEditText(index)
                }
            }
        })
    }

    private fun moveFocusToNextEditText(index: Int) {
        if (index < editTexts.size - 1) {
            // Move focus to the next EditText
            editTexts[index + 1]?.requestFocus()
        } else {


            // If last EditText, you can perform an action (e.g., submit the passcode)
            // For example, you might want to implement a method to handle passcode submission.
            // submitPasscode()
        }
    }

//    fun register() {
//        val firebaseAuth = Firebase.auth
//        firebaseAuth.createUserWithEmailAndPassword(
//            binding..text.toString(),
//            binding..text.toString())
//            .addOnSuccessListener {
//                findNavController().popBackStack()
//            }.addOnFailureListener {
//                (it as? FirebaseAuthException)?.errorCode?.let { errorCode->
//                    Toast.makeText(context,"Problem creating account",Toast.LENGTH_SHORT).show()
//                }
//
//            }
//    }

}
