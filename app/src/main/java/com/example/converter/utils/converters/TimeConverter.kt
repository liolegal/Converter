package com.example.converter.utils.converters

class TimeConverter:Converter {
    override fun convert(unit1: String, unit2: String, input: Double): Double {
        var result = 0.0
        if (input != 0.0) {
            when (unit2) {
                "Seconds" -> result = convertToSeconds(input, unit1)
                "Minutes" -> result = convertToMinutes(input, unit1)
                "Hours" -> result = convertToHours(input, unit1)
                "Days" -> result = convertToDays(input, unit1)
            }
        }
        return result
    }
    private fun convertToSeconds(value: Double, typeOValue: String): Double{
        var result = 0.0
        when (typeOValue) {
            "Minutes" -> result = value * 60
            "Seconds" -> result = value
            "Hours" -> result = value * 3600
            "Days" -> result = value * 3600 * 24
        }
        return result
    }
    private fun convertToMinutes(value: Double, typeOValue: String): Double{
        var result = 0.0
        when (typeOValue) {
            "Minutes" -> result = value
            "Seconds" -> result = value / 60
            "Hours" -> result = value * 60
            "Days" -> result = value * 24 * 60
        }
        return result
    }
    private fun convertToHours(value: Double, typeOValue: String): Double{
        var result = 0.0
        when (typeOValue) {
            "Minutes" -> result = value / 60
            "Seconds" -> result = value / 3600
            "Hours" -> result = value
            "Days" -> result = value * 24
        }
        return result
    }
    private fun convertToDays(value: Double, typeOValue: String): Double{
        var result = 0.0
        when (typeOValue) {
            "Minutes" -> result = value / 60 / 24
            "Seconds" -> result = value/3600/24
            "Hours" -> result = value /24
            "Days" -> result = value
        }
        return result
    }
}