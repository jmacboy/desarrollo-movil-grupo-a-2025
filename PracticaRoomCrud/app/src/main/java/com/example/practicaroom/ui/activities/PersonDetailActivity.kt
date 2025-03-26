package com.example.practicaroom.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaroom.R
import com.example.practicaroom.databinding.ActivityPersonDetailBinding
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.ui.viewmodels.PersonDetailViewModel

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
        setupEventListeners()
        id = intent.getIntExtra(PARAM_ID, 0)
        if (id == 0) {
            return
        }
        viewModel.loadPerson(this, id)
    }

    private fun setupEventListeners() {
        binding.btnDetailSave.setOnClickListener { doSave() }
        binding.btnDetailCancel.setOnClickListener { finish() }
    }

    private fun doSave() {
        val person = viewModel.person.value ?: Person()

        person.apply {
            age = binding.txtAge.text.toString().toInt()
            name = binding.txtName.text.toString()
            phone = binding.txtPhone.text.toString()
            email = binding.txtEmail.text.toString()
            lastName = binding.txtLastName.text.toString()
        }
        viewModel.savePerson(this, person)

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
        viewModel.hasErrorSaving.observe(this) {
            if (it) {
                Toast.makeText(this, getString(R.string.error_saving_person), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.personSaved.observe(this) {
            if (it != null) {
                val isInsert = id == 0
                Log.d("RESULT", "Is insert sending $isInsert")
                val resultIntent = MainActivity.returnIntent(this, isInsert, it)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    companion object {
        fun detailIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PersonDetailActivity::class.java)
            intent.putExtra(PARAM_ID, id)
            return intent
        }

        fun createIntent(context: Context): Intent {
            return Intent(context, PersonDetailActivity::class.java)
        }

        private const val PARAM_ID = "id"
    }
}