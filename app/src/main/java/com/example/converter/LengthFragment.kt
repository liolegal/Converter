package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentLenghtBinding

class LengthFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentLenghtBinding
    private var input: Double = 0.0
    private var result: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLenghtBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.inputData.observe(activity as LifecycleOwner) {
            if (it.isNotEmpty()) {
                input = it.toDouble()
            }
            binding.tvInput.text = it
        }
//        dataModel.typeOfValue.observe(activity as LifecycleOwner) {
//            binding.tvInput.text = ""
//            dataModel.inputData.value = ""
//            binding.tvTypeOfValue.text = it
//
//
//        }
        var selectedInput: String
        var selectedOutput: String
        binding.btnConvert.setOnClickListener {

            val converter = LengthConverter()
            selectedInput = binding.spinnerInput.selectedItem.toString()
            selectedOutput = binding.spinnerOutput.selectedItem.toString();
            if (input != 0.0) {
                when (selectedOutput) {
                    "metres" -> result = converter.convertToMetres(input, selectedInput)
                    "cm" -> result = converter.convertToCm(input, selectedInput)
                    "Inches" -> result = converter.convertToInches(input, selectedInput)
                    "lb" -> result = converter.convertToLb(input, selectedInput)
                }
                binding.tvOutput.text = result.toString()
            }
        }
        binding.btnSwapValues.setOnClickListener {
            input = result
            binding.tvInput.text = result.toString()
            dataModel.inputData.value=result.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LengthFragment()
    }



}