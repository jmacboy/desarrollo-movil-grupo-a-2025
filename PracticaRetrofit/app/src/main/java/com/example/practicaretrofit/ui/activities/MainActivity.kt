package com.example.practicaretrofit.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaretrofit.R
import com.example.practicaretrofit.databinding.ActivityMainBinding
import com.example.practicaretrofit.ui.adapters.PostAdapter
import com.example.practicaretrofit.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
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
        viewModel.getPostList()
    }

    private fun setupRecyclerView() {
        val adapter = PostAdapter(arrayListOf())
        binding.rvPostList.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this@MainActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
            this.adapter = adapter
        }
    }

    private fun setupViewModelObservers() {
        viewModel.postList.observe(this) {
            val adapter = binding.rvPostList.adapter as PostAdapter
            adapter.setData(it)
        }
    }


}