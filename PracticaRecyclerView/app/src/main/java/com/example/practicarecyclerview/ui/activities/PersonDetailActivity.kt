package com.example.practicarecyclerview.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.databinding.ActivityPersonDetailBinding
import com.example.practicarecyclerview.ui.viewmodels.PersonDetailViewModel

class PersonDetailActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var binding: ActivityPersonDetailBinding
    private val viewModel: PersonDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()

        id = intent.getIntExtra(PARAM_ID, 0)
        viewModel.loadPerson(id)
    }

    private fun setupViewModelObservers() {
        viewModel.person.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtAge.setText(it.age.toString())
            binding.txtName.setText(it.name)
            binding.txtPhone.setText(it.phone)
            binding.txtEmail.setText(it.email)
            binding.txtLastName.setText(it.lastName)
        }
    }

    companion object {
        fun detailIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PersonDetailActivity::class.java)
            intent.putExtra(PARAM_ID, id)
            return intent
        }

        private const val PARAM_ID = "id"
    }
}