package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentKeyboardBinding

class KeyboardFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentKeyboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentKeyboardBinding.inflate(inflater)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var string=""
        dataModel.inputData.observe(activity as LifecycleOwner) {
            string=it
        }
        binding.btn0.setOnClickListener{
            if(string!="0"){
                string+="0"
                dataModel.inputData.value=string
            }

        }
        binding.btn1.setOnClickListener{
            string+="1"
            dataModel.inputData.value=string
        }
        binding.btn2.setOnClickListener{
            string+="2"
            dataModel.inputData.value=string
        }
        binding.btn3.setOnClickListener{
            string+="3"
            dataModel.inputData.value=string
        }
        binding.btn4.setOnClickListener{
            string+="4"
            dataModel.inputData.value=string
        }
        binding.btn5.setOnClickListener{
            string+="5"
            dataModel.inputData.value=string
        }
        binding.btn6.setOnClickListener{
            string+="6"
            dataModel.inputData.value=string
        }
        binding.btn7.setOnClickListener{
            string+="7"
            dataModel.inputData.value=string
        }
        binding.btn8.setOnClickListener{
            string+="8"
            dataModel.inputData.value=string
        }
        binding.btn9.setOnClickListener{
            string+="9"
            dataModel.inputData.value=string
        }
        binding.backBtn.setOnClickListener{
            if(string!=""){
                string=string.dropLast(1)
            }else dataModel.inputData.value=""

            dataModel.inputData.value=string
        }
        binding.dotBtn.setOnClickListener{
            if(string==""){
                string+="0."
            }else if(!string.contains(".")){
                string+="."
            }
            dataModel.inputData.value=string
        }






    }
    companion object {
     @JvmStatic
        fun newInstance()=KeyboardFragment()
    }
}