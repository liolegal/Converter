package com.example.converter

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.ClipboardManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentLenghtBinding
import com.example.converter.utils.converters.Converter
import com.example.converter.utils.converters.LengthConverter
import com.example.converter.utils.converters.TimeConverter
import com.example.converter.utils.converters.WeightConverter


class DataFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentLenghtBinding
    private var input: Double = 0.0
    private var result: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLenghtBinding.inflate(inflater)
        val adapter = setUI("Length")
        binding.spinnerInput.adapter = adapter
        binding.spinnerOutput.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.inputData.observe(activity as LifecycleOwner) {
            if (it.isNotEmpty()) {
                input = it.toDouble()
            }
            binding.tvInput.text = it
            binding.tvOutput.text=""
        }
        dataModel.typeOfValue.observe(activity as LifecycleOwner) {
            when (it) {
                activity?.getString(R.string.length) -> {
                    val adapter = setUI("Length")
                    binding.spinnerInput.adapter = adapter
                    binding.spinnerOutput.adapter = adapter
                }
                activity?.getString(R.string.weight) -> {
                    val adapter = setUI("Weight")
                    binding.spinnerInput.adapter = adapter
                    binding.spinnerOutput.adapter = adapter
                }
                activity?.getString(R.string.time) -> {
                    val adapter = setUI("Time")
                    binding.spinnerInput.adapter = adapter
                    binding.spinnerOutput.adapter = adapter
                }
            }
        }




        binding.btnConvert.setOnClickListener {
            var converter:Converter=LengthConverter()
            when (dataModel.typeOfValue.value) {
                activity?.getString(R.string.length) -> {
                    converter=LengthConverter()
                }
                activity?.getString(R.string.weight) -> {
                    converter=WeightConverter()
                }
                activity?.getString(R.string.time) -> {
                    converter=TimeConverter()
                }
            }
            val selectedInput = binding.spinnerInput.selectedItem.toString()
            val selectedOutput = binding.spinnerOutput.selectedItem.toString()
            result = converter.convert(selectedInput, selectedOutput, input)
            binding.tvOutput.text = result.toString()
        }

        binding.btnSwapValues.setOnClickListener {
            input = result
            binding.tvOutput.text = ""
            dataModel.inputData.postValue(result.toString())
        }

        binding.copyBtn?.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                val clipboard =
                    context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.text = binding.tvOutput.text
            } else {
                val clipboard =
                    context!!.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = ClipData.newPlainText("Copied Text", binding.tvOutput.text)
                clipboard.setPrimaryClip(clip)
            }
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DataFragment()
    }

    private fun setUI(type: String): ArrayAdapter<*> {
        lateinit var adapter: ArrayAdapter<*>
        when (type) {
            "Length" -> {
                binding.tvTypeOfValue.text = "Length"
                adapter = ArrayAdapter.createFromResource(
                    requireContext(), R.array.length_array,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                return adapter
            }
            "Weight" -> {
                binding.tvTypeOfValue.text = "Weight"
                adapter = ArrayAdapter.createFromResource(
                    requireContext(), R.array.weight_array,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                return adapter
            }
            "Time" -> {
                binding.tvTypeOfValue.text = "Time"
                adapter = ArrayAdapter.createFromResource(
                    requireContext(), R.array.time_array,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                return adapter
            }

        }
        return adapter
    }
}

