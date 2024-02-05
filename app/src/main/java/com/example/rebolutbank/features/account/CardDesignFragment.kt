package com.example.rebolutbank.features.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.rebolutbank.R
import com.example.rebolutbank.R.color
import com.example.rebolutbank.R.color.black
import com.example.rebolutbank.R.color.brown
import com.example.rebolutbank.R.color.ligthgrey
import com.example.rebolutbank.R.color.purple
import com.example.rebolutbank.R.color.yellow
import com.example.rebolutbank.databinding.FragmentCardDesignBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDesignFragment : Fragment(R.layout.fragment_card_design) {

    private lateinit var binding: FragmentCardDesignBinding
    val viewModel: CardDesignViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardDesignBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // Initialize your ViewModel

        // Observe changes in bg_card and update the card's background
        viewModel.blackbutton.observe(viewLifecycleOwner){
            if (it){
                context?.let { it1 -> binding.cardfg.setCardBackgroundColor(it1.getColor(black)) }
                context?.let { it1 -> binding.cardbg.setCardBackgroundColor(it1.getColor(black)) }
                viewModel.widthtbtn.value= "55dp"
                viewModel.heightbtn.value = "60dp"
            }
        }
        viewModel.yellowbutton.observe(viewLifecycleOwner){
            if (it){
                context?.let { it1 -> binding.cardfg.setCardBackgroundColor(it1.getColor(yellow)) }
                context?.let { it1 -> binding.cardbg.setCardBackgroundColor(it1.getColor(yellow)) }
            }
        }
        viewModel.purplebutton.observe(viewLifecycleOwner){
            if (it){
                context?.let { it1 -> binding.cardfg.setCardBackgroundColor(it1.getColor(purple)) }
                context?.let { it1 -> binding.cardbg.setCardBackgroundColor(it1.getColor(purple)) }
            }
        }
        viewModel.lightbluebutton.observe(viewLifecycleOwner){
            if (it){
                context?.let { it1 -> binding.cardfg.setCardBackgroundColor(it1.getColor(brown)) }
                context?.let { it1 -> binding.cardbg.setCardBackgroundColor(it1.getColor(brown)) }
            }
        }
        viewModel.pinkbutton.observe(viewLifecycleOwner){
            if (it){
                context?.let { it1 -> binding.cardfg.setCardBackgroundColor(it1.getColor(ligthgrey)) }
                context?.let { it1 -> binding.cardbg.setCardBackgroundColor(it1.getColor(ligthgrey)) }
            }
        }
        return binding.root
    }

}
