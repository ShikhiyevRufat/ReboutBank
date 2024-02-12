package com.example.rebolutbank.features.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rebolutbank.MainActivity
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    private lateinit var binding : FragmentMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater)

        binding.goAccount.setOnClickListener {
            // Hide bottom navigation bar
            (requireActivity() as MainActivity).hideBottomNavigation()

            // Navigate to AccountFragment
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragmentContainerView, AcountFragment())
                .commit()
        }



        return binding.root
    }

}