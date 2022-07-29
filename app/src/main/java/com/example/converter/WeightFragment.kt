package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentLenghtBinding
import com.example.converter.databinding.FragmentWeightBinding

class WeightFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentWeightBinding
    var input: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentWeightBinding.inflate(inflater)
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
        dataModel.typeOfValue.observe(activity as LifecycleOwner) {
            binding.tvInput.text = ""
            dataModel.inputData.value = ""
            binding.tvTypeOfValue.text = it
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = WeightFragment()

    }
}