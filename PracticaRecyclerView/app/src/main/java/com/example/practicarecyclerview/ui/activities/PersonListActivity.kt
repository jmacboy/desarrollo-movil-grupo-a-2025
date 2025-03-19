package com.example.practicarecyclerview.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.databinding.ActivityPersonListBinding
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.ui.adapters.PersonAdapter
import com.example.practicarecyclerview.ui.viewmodels.PersonListViewModel


class PersonListActivity : AppCompatActivity(), PersonAdapter.OnPersonClickListener {
    private lateinit var binding: ActivityPersonListBinding
    private val viewModel: PersonListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelObservers()
        viewModel.loadData()
    }

    private fun setupViewModelObservers() {
        viewModel.personList.observe(this) {
            val adapter = binding.lstPersons.adapter as PersonAdapter
            adapter.setData(it)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonAdapter(mutableListOf())
        val dividerItemDecoration = DividerItemDecoration(
            binding.lstPersons.context,
            LinearLayoutManager.VERTICAL
        )
        binding.lstPersons.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonListActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(dividerItemDecoration)
        }
        adapter.setOnPersonClickListener(this)

    }

    override fun onPersonClick(person: Person) {
        val id = person.id
        val intent = PersonDetailActivity.detailIntent(this, id)
        startActivity(intent)
    }

}