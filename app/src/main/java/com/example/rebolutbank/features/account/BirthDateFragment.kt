package com.example.rebolutbank.features.account

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rebolutbank.R
import com.example.rebolutbank.databinding.FragmentBirthDateBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class BirthDateFragment : Fragment() {

    private val calendar = Calendar.getInstance()
    private lateinit var binding: FragmentBirthDateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBirthDateBinding.inflate(inflater)

        binding.dateday.setOnClickListener {
            showDatePicker()
        }

        binding.datemonth.setOnClickListener {
            showDatePicker()
        }

        binding.dateyear.setOnClickListener {
            showDatePicker()
        }

        binding.nextmailsign.setOnClickListener {
            gotoMailSignpage()
        }

        return binding.root
    }

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireActivity(), {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.dateday.text = Editable.Factory.getInstance().newEditable("${formattedDate.subSequence(0,2)}")
                binding.datemonth.text = Editable.Factory.getInstance().newEditable("${formattedDate.subSequence(3,5)}")
                binding.dateyear.text = Editable.Factory.getInstance().newEditable("${formattedDate.subSequence(6,10)}")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

    fun gotoMailSignpage(){
        if (binding.dateday.text.isNullOrEmpty()){
            binding.anytxt2.text = "Add your birthday please"
            binding.anytxt2.setTextColor(Color.RED)
        }
        else{
            val action = BirthDateFragmentDirections.actionBirthDateFragmentToMailSignFragment()
            findNavController().navigate(action)
        }
    }

}