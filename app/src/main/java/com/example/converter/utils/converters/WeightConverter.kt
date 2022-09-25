package com.example.converter.utils.converters

class WeightConverter : Converter {
    override fun convert(unit1: String, unit2: String, input: Double): Double {
        var result = 0.0
        if (input != 0.0) {
            when (unit2) {
                "kg" -> result = convertToKilograms(input, unit1)
                "gramm" -> result = convertToGramms(input, unit1)
                "Ounce(oz)" -> result = convertToOunce(input, unit1)
                "Pound(lb)" -> result = convertToPound(input, unit1)
            }
        }
        return result
    }

    private fun convertToKilograms(value: Double, typeOValue: String): Double {
        var result = 0.0
        when (typeOValue) {
            "kg" -> result = value
            "gramm" -> result = value / 1000
            "Ounce(oz)" -> result = value / 35.274
            "Pound(lb)" -> result = value / 2.205
        }
        return result
    }

    private fun convertToGramms(value: Double, typeOValue: String): Double {
        var result = 0.0
        when (typeOValue) {
            "kg" -> result = value * 1000
            "gramm" -> result = value
            "Ounce(oz)" -> result = value * 28.35
            "Pound(lb)" -> result = value * 453.6
        }
        return result
    }

    private fun convertToOunce(value: Double, typeOValue: String): Double {
        var result = 0.0
        when (typeOValue) {
            "kg" -> result = value * 35.274
            "gramm" -> result = value/28.35
            "Ounce(oz)" -> result = value
            "Pound(lb)" -> result = value * 16
        }
        return result
    }

    private fun convertToPound(value: Double, typeOValue: String): Double {
        var result = 0.0
        when (typeOValue) {
            "kg" -> result = value * 2.205
            "gramm" -> result = value*453.6
            "Ounce(oz)" -> result = value /16
            "Pound(lb)" -> result = value
        }
        return result
    }
}