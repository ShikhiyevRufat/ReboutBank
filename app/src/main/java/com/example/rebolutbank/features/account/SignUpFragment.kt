package com.example.rebolutbank.features.account

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    val viewModel : SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        signUp()

        binding.nextdate.setOnClickListener {
            nextdateaction()
        }

        return binding.root
    }

    private fun signUp(){
        binding.nameuser.setOnFocusChangeListener{_,focused->
            if(!focused){
                binding.nameuserlayout.helperText = valid_name_lastname()

            }
        }

        binding.lastnameuser.setOnFocusChangeListener{_, focused->
            if(!focused){
                binding.lastnameuserlayout.helperText = valid_name_lastname()
            }
        }
    }

    private fun nextdateaction(){

        if (binding.nameuser.text.isNullOrEmpty()){
            binding.anytxt2.text = "Add your Name"
            binding.anytxt2.setTextColor(Color.RED)
        }
        else if (binding.lastnameuser.text.isNullOrEmpty()){
            binding.anytxt2.text = "Add your Lastname"
            binding.anytxt2.setTextColor(Color.RED)
        }
        else if(binding.usercountry.text.isNullOrEmpty()){
            binding.anytxt2.text = "Add your Country"
            binding.anytxt2.setTextColor(Color.RED)
        }
        else{
            var action = SignUpFragmentDirections.actionSignUpFragmentToBirthDateFragment()
            findNavController().navigate(action)
        }

    }

    private fun valid_name_lastname() : String? {

        val nameText = binding.nameuser.text.toString()
        val lastnameText = binding.lastnameuser.text.toString()


        fun isAlpha(input: String): Boolean {
            return input.all { it.isLetter() }
        }

        if (!isAlpha(nameText) || !isAlpha(lastnameText)) {
            return "Use only alpha"
        }
        // Function to check if a string consists only of alphabetic characters
        return null
    }





}