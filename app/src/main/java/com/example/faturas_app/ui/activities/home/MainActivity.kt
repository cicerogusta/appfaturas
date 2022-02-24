package com.example.faturas_app.ui.activities.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }





}