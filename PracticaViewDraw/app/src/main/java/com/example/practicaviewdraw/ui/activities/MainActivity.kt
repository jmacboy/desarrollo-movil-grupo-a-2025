package com.example.practicaviewdraw.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaviewdraw.R
import com.example.practicaviewdraw.databinding.ActivityMainBinding
import com.example.practicaviewdraw.ui.components.Shape

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
        binding.btnDrawRectangle.setOnClickListener {
            binding.board.setShape(Shape.RECTANGLE)
        }
        binding.btnCircle.setOnClickListener {
            binding.board.setShape(Shape.CIRCLE)
        }
        binding.btnLine.setOnClickListener {
            binding.board.setShape(Shape.LINE)
        }
    }
}