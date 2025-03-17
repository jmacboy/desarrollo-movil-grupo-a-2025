package com.example.practicacomponentes.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacomponentes.R
import com.example.practicacomponentes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnShowValues.setOnClickListener {
            val val1 = binding.numberPicker.value
            val val2 = binding.numberPicker2.value
            Toast.makeText(this, "Valor 1: $val1\nValor2: $val2", Toast.LENGTH_SHORT).show()
        }
        binding.numberPicker.setOnValueChangedListener {
            Toast.makeText(this, "Valor cambiado a ${it}", Toast.LENGTH_SHORT).show()
        }
        binding.numberPicker2.setOnValueChangedListener {
            Toast.makeText(this, "Valor cambiado a ${it}", Toast.LENGTH_SHORT).show()
        }
        binding.btnChangeValues.setOnClickListener {
            binding.numberPicker.value = 5
            binding.numberPicker2.value = 10
        }
    }
}