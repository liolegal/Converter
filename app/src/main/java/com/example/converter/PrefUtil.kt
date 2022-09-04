package com.example.converter

import android.content.Context
import android.preference.PreferenceManager

class PrefUtil {
    companion object {

        private const val UNIT_TYPE = "converter.unitType"
        fun getUnitType(context: Context): String? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(UNIT_TYPE, "")
        }

        fun setUnitType(type: String, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(UNIT_TYPE, type)
            editor.apply()
        }
    }
}