package com.example.converter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Converter)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.keyboard_frameLayout, KeyboardFragment.newInstance())
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.data_frameLayout, DataFragment.newInstance())
            .commit()

    }

    override fun onResume() {
        super.onResume()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.keyboard_frameLayout, KeyboardFragment.newInstance())
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.data_frameLayout, DataFragment.newInstance())
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.values_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.length_menu_item ->{
                dataModel.inputData.value=""
                dataModel.typeOfValue.value="Length"

            }
            R.id.weight_menu_item ->{
                dataModel.inputData.value=""
                dataModel.typeOfValue.value="Weight"
            }
            R.id.time_menu_item ->{
                dataModel.inputData.value=""
                dataModel.typeOfValue.value="Time"
            }

        }


        return true
    }
}


