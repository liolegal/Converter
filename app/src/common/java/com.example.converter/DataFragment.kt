package com.example.converter

import android.content.ClipData
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build
import android.os.Bundle
import android.text.ClipboardManager
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentDataBinding
import com.example.converter.utils.converters.Converter
import com.example.converter.utils.converters.LengthConverter
import com.example.converter.utils.converters.TimeConverter
import com.example.converter.utils.converters.WeightConverter


class DataFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentDataBinding

    private var clipboard:android.content.ClipboardManager?  = null

    private var input: Double = 0.0
    private var result: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater)
        clipboard = context!!.getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager?
        val adapter = setUI("Length")
        binding.spinnerInput.adapter = adapter
        binding.spinnerOutput.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dataModel.inputData.observe(activity as LifecycleOwner) {

            input = if (it.isNotEmpty()) {
                it.toDouble()
            } else 0.0
            binding.etInput?.setText(it)
            binding.etOutput?.setText("")


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
            var converter: Converter = LengthConverter()
            when (dataModel.typeOfValue.value) {
                activity?.getString(R.string.length) -> {
                    converter = LengthConverter()
                }
                activity?.getString(R.string.weight) -> {
                    converter = WeightConverter()
                }
                activity?.getString(R.string.time) -> {
                    converter = TimeConverter()
                }
            }
            val selectedInput = binding.spinnerInput.selectedItem.toString()
            val selectedOutput = binding.spinnerOutput.selectedItem.toString()
            if (input <= Int.MAX_VALUE) {
                result = converter.convert(selectedInput, selectedOutput, input)
                if (result <= Double.MAX_VALUE) {
                    binding.etOutput?.setText(result.toBigDecimal().toPlainString())
                } else Toast.makeText(context, "Enter error", Toast.LENGTH_SHORT)
                    .show()

            } else Toast.makeText(context, "Enter error", Toast.LENGTH_SHORT)
                .show()


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
                adapter = ArrayAdapter.createFromResource(
                    requireContext(), R.array.length_array,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                return adapter
            }
            "Weight" -> {
                adapter = ArrayAdapter.createFromResource(
                    requireContext(), R.array.weight_array,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                return adapter
            }
            "Time" -> {
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

