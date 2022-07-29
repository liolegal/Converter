package com.example.converter

class LengthConverter {

    fun convertToMetres(value:Double,typeOValue:String ):Double{
        var result=0.0
        when(typeOValue){
            "cm" -> result=value*0.01
            "Inches" -> result=value/39.37
            "lb" -> result=value/3.281
            "metres"-> result=value
        }
        return result
    }
    fun convertToCm(value:Double,typeOValue:String ):Double {
        var result=0.0
        when(typeOValue){
            "metres" -> result=value*100
            "Inches" -> result=value*2.54
            "lb" -> result=value*30.48
            "cm"-> result=value
        }
        return result
    }
    fun convertToInches(value:Double,typeOValue:String ):Double {
        var result = 0.0
        when(typeOValue){
            "metres" -> result=value*39.37
            "cm" -> result=value/2.54
            "lb" -> result=value*12
            "Inches"-> result=value
        }
        return result
    }
    fun convertToLb(value:Double,typeOValue:String ):Double {
        var result = 0.0
        when(typeOValue){
            "metres" -> result=value*3.281
            "cm" -> result=value/30.48
            "Inches" -> result=value/12
            "lb"-> result=value
        }
        return result
    }

}