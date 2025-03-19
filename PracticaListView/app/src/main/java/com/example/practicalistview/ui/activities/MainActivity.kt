package com.example.practicalistview.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicalistview.R
import com.example.practicalistview.databinding.ActivityMainBinding
import com.example.practicalistview.ui.adapters.NamesArrayAdapter

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
        setupListView()
    }

    private fun setupListView() {
        val names = arrayOf(
            "John",
            "Jane",
            "Alice",
            "Bob",
            "Charlie",
            "David",
            "Eve",
            "Frank",
            "Grace",
            "Hank",
            "Ivy",
            "Jack",
            "Kate",
            "Liam",
            "Mia",
            "Noah",
            "Olivia",
            "Peter",
            "Quinn",
            "Ryan",
            "Sara",
            "Tom",
            "Uma",
            "Vince",
            "Wendy",
            "Xander",
            "Yara",
            "Zack"
        )
        val adapter = NamesArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        binding.lstNames.adapter = adapter
    }
}