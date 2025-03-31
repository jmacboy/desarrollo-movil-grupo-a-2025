package com.example.practicaroom.ui.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaroom.R
import com.example.practicaroom.databinding.ActivityPersonDetailBinding
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.Phone
import com.example.practicaroom.ui.adapters.PhoneAdapter
import com.example.practicaroom.ui.viewmodels.PersonDetailViewModel


class PersonDetailActivity : AppCompatActivity(), PhoneAdapter.OnPhoneClickListener {
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
        setupPhoneRecyclerView()

        id = intent.getIntExtra(PARAM_ID, 0)
        if (id == 0) {
            return
        }

        viewModel.loadPerson(this, id)
    }

    private fun setupPhoneRecyclerView() {
        val adapter = PhoneAdapter(mutableListOf())
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvPhones.context,
            LinearLayoutManager.VERTICAL
        )
        binding.rvPhones.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonDetailActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(dividerItemDecoration)
        }
        adapter.setOnPhoneClickListener(this)
    }

    private fun setupEventListeners() {
        binding.btnDetailSave.setOnClickListener { doSave() }
        binding.btnDetailCancel.setOnClickListener { finish() }
        binding.btnPhoneAdd.setOnClickListener { showPhoneDialog() }
    }

    private fun showPhoneDialog() {
        val dialogFragment = PhoneDialogFragment()
        dialogFragment.show(supportFragmentManager, "PhoneDialog")
    }

    private fun doSave() {
        val person = viewModel.person.value ?: Person()

        person.apply {
            age = binding.txtAge.text.toString().toInt()
            name = binding.txtName.text.toString()
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

    override fun onPhoneClick(phone: Phone) {

    }

    class PhoneDialogFragment(val viewModel: PersonDetailViewModel) : DialogFragment() {


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                val inflater = it.layoutInflater
                builder.setView(inflater.inflate(R.layout.phone_dialog_layout, null))
                    // Add action buttons.
                    .setPositiveButton(
                        "OK"
                    ) { dialog, id ->
                        val dialog  = dialog as AlertDialog
                        val txtPhoneNumber = dialog.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.txtPhoneNumber)
                        val phoneNumber = txtPhoneNumber?.text.toString()

                        val phoneTypeSpinner = dialog.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.spPhoneType)
                        val phoneType = phoneTypeSpinner?.text.toString()
                        viewModel.savePhone(phoneNumber, phoneType)


                    }
                    .setNegativeButton(
                        "Cancel"
                    ) { dialog, id ->
                        getDialog()?.cancel()
                    }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}