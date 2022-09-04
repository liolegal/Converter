package com.example.converter.utils.converters

interface Converter {
    fun convert(unit1: String, unit2: String, input: Double): Double
}