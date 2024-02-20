package com.gizemir.storingdatanew

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gizemir.storingdatanew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences// tanımladık
    var ageFromPref: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("com.gizemir.storingdatanew", MODE_PRIVATE)//initialize ettik
        ageFromPref =sharedPref.getInt("age", -1)

        if(ageFromPref == -1){
            binding.textView.text = "Your age: "
        }else{
            binding.textView.text = "Your age: ${ageFromPref}"
        }
    }
    fun save(view: View){
        val myAge = binding.editText.text.toString().toIntOrNull() //kullanıcıdan editTexte veri aldık


        if(myAge != null){
            binding.textView.text = "your age is ${myAge}" //alınan veriyi textView'de yansıttık
            // binding.textView.text = "your age is  " +age  //alınan veriyi textView'de yansıttık
            sharedPref.edit().putInt("age", myAge).apply()
        }
    }
    fun delete(view:View){
        ageFromPref =sharedPref.getInt("age" ,-1)
        if(ageFromPref != -1){
            sharedPref.edit().remove("age").apply()
            binding.textView.text = "Your age: "
        }

    }
}