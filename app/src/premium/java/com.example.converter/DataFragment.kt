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
import kotlin.math.roundToInt


class DataFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentDataBinding
    private var clipboard: android.content.ClipboardManager? = null
    private var input: Double = 0.0
    private var result: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater)
        clipboard =
            context!!.getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager?
        val adapter = setUI("Length")
        binding.spinnerInput.adapter = adapter
        binding.spinnerOutput.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.etInput.showSoftInputOnFocus = false
        dataModel.inputData.observe(activity as LifecycleOwner) {
            var text = binding.etInput.text!!
            when (it) {
                "back" -> {
                    if (text.isNotEmpty() && binding.etInput.selectionStart!=0) {
                        text.delete(
                            binding.etInput.selectionStart - 1,
                            binding.etInput.selectionStart
                        )
                    }
                }
                "." -> {
                    if (text.toString() == "") {
                        binding.etInput.setText("0.")
                    } else if (!text.toString().contains(".")) {
                        binding.etInput.text?.insert(binding.etInput.selectionStart, ".")
                    }
                }
                "0" -> {
                    if (text.toString() != "0") {
                        binding.etInput.text?.insert(binding.etInput.selectionStart, it)
                    }
                }
                else -> {
                    if (binding.etInput.text?.length!! <= 15) {
                        binding.etInput.text?.insert(binding.etInput.selectionStart, it)
                    }
                }
            }
            binding.etOutput.setText("")


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
            input = if (binding.etInput.text?.isNotEmpty() == true) {
                binding.etInput.text.toString().toDouble()
            } else 0.0

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
            result = converter.convert(selectedInput, selectedOutput, input)
            binding.etOutput.setText(result.toBigDecimal().toPlainString())


        }
        binding.btnSwapValues.setOnClickListener {
            if (result.toString().length <= 15) {
                binding.etInput.setText(result.toBigDecimal().toPlainString())
            }
        }
        binding.btnSwapUnits.setOnClickListener {
            val buf = binding.spinnerOutput.selectedItemPosition
            binding.spinnerOutput.setSelection(binding.spinnerInput.selectedItemPosition)
            binding.spinnerInput.setSelection(buf)
        }
        binding.pasteBtn.setOnClickListener {
            try {
                val abc = clipboard?.primaryClip
                val item = abc?.getItemAt(0)?.text
                if (item?.toString()?.length!! + (binding.etInput.text?.length!!) <= 15) {
                    binding.etInput.text?.insert(binding.etInput.selectionStart, item.toString())
                } else Toast.makeText(context, "Too much digits", Toast.LENGTH_SHORT).show()

            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Invalid data", Toast.LENGTH_SHORT).show()
            } catch (E: Exception) {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            }


        }
        binding.copyBtn.setOnClickListener {
            val clip = ClipData.newPlainText("Copied Text", binding.etOutput.text)
            clipboard?.setPrimaryClip(clip)
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DataFragment()
    }

    override fun onResume() {
        super.onResume()
        binding.etInput.setText(dataModel.data.value)
    }

    override fun onStop() {
        super.onStop()
        dataModel.data.value=binding.etInput.text.toString()
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

