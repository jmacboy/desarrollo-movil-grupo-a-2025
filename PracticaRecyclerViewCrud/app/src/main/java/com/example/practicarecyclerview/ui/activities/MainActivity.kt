package com.example.practicarecyclerview.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.databinding.ActivityMainBinding
import com.example.practicarecyclerview.ui.adapters.NamesAdapter
import com.example.practicarecyclerview.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModels()

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
        setupRecyclerView()
        setupViewModelObservers()
        viewmodel.loadData()
    }

    private fun setupViewModelObservers() {
        viewmodel.nameList.observe(this){
            val adapter = binding.lstNames.adapter as NamesAdapter
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {

        val adapter = NamesAdapter(arrayOf())
        binding.lstNames.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
    }
}