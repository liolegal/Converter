package com.example.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel:ViewModel() {
    val inputData:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val typeOfValue:MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }
}