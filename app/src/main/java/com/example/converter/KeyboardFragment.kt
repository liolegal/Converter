package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.converter.databinding.FragmentKeyboardBinding

class KeyboardFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentKeyboardBinding
    var string=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentKeyboardBinding.inflate(inflater)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dataModel.inputData.observe(activity as LifecycleOwner) {
            string=it
        }
        binding.btn0.setOnClickListener{
            onKeyboardClick("0")
        }
        binding.btn1.setOnClickListener{
            onKeyboardClick("1")
        }
        binding.btn2.setOnClickListener{
            onKeyboardClick("2")
        }
        binding.btn3.setOnClickListener{
            onKeyboardClick("3")
        }
        binding.btn4.setOnClickListener{
            onKeyboardClick("4")
        }
        binding.btn5.setOnClickListener{
            onKeyboardClick("5")
        }
        binding.btn6.setOnClickListener{
            onKeyboardClick("6")
        }
        binding.btn7.setOnClickListener{
            onKeyboardClick("7")
        }
        binding.btn8.setOnClickListener{
            onKeyboardClick("8")
        }
        binding.btn9.setOnClickListener{
            onKeyboardClick("9")
        }
        binding.backBtn.setOnClickListener{
//            if(string!=""){
//                string=string.dropLast(1)
//            }else dataModel.inputData.value=""

            dataModel.inputData.value="back"
        }
        binding.dotBtn.setOnClickListener{
//            if(string==""){
//                onKeyboardClick("0.")
//            }else if(!string.contains(".")){
//                onKeyboardClick(".")
//            }
            onKeyboardClick(".")
        }






    }
    private fun onKeyboardClick(digit:String){
//        if(string.length<15){
//            string+=digit
//            dataModel.inputData.value=string
//        }else Toast.makeText(context,"Too more digits",Toast.LENGTH_SHORT).show()
        dataModel.inputData.value=digit
    }

    companion object {
     @JvmStatic
        fun newInstance()=KeyboardFragment()
    }
}