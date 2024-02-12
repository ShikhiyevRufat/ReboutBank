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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.databinding.FragmentPassCodeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassCodeFragment : Fragment() {

    private lateinit var binding: FragmentPassCodeBinding
    private val editTexts = arrayOfNulls<EditText>(6)
    private val firestore = Firebase.firestore
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPassCodeBinding.inflate(inflater)
        var editTextIds = arrayOf(
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
            useradddata()
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

private fun useradddata () {

}

    private fun moveFocusToNextEditText(index: Int) {
        if (index < editTexts.size - 1) {
            // Move focus to the next EditText
            editTexts[index + 1]?.requestFocus()
        } else {

        }
    }


}
