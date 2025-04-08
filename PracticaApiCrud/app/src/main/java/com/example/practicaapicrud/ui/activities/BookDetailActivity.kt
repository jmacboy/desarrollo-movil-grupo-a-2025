package com.example.practicaapicrud.ui.activities

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
import com.example.practicaapicrud.R
import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.databinding.ActivityBookDetailBinding
import com.example.practicaapicrud.ui.viewmodels.BookDetailViewModel

class BookDetailActivity : AppCompatActivity() {
    private var id: Long = 0
    private lateinit var binding: ActivityBookDetailBinding
    private val viewModel: BookDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupEventListeners()

        id = intent.getLongExtra(PARAM_ID, 0)
        if (id == 0L) {
            return
        }
        viewModel.loadBook(id)
    }

    private fun setupEventListeners() {
        binding.btnSaveBookDetail.setOnClickListener { doSave() }
        binding.btnCancelBookDetail.setOnClickListener { finish() }
    }

    private fun doSave() {
        val book = viewModel.book.value ?: Book()

        book.apply {
            nombre = binding.txtBookName.editText?.text.toString()
            autor = binding.txtBookAuthor.editText?.text.toString()
            imagen = binding.txtBookImg.editText?.text.toString()
            editorial = binding.txtBookEditorial.editText?.text.toString()
            isbn = binding.txtIsbn.editText?.text.toString()
            sinopsis = binding.txtSinopsis.editText?.text.toString()
        }
        viewModel.saveBook(book)
    }

    private fun setupViewModelObservers() {
        viewModel.book.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtBookName.editText?.setText(it.nombre)
            binding.txtBookAuthor.editText?.setText(it.autor)
            binding.txtBookEditorial.editText?.setText(it.editorial)
            binding.txtIsbn.editText?.setText(it.isbn)
            binding.txtSinopsis.editText?.setText(it.sinopsis)
            binding.txtBookImg.editText?.setText(it.imagen)
        }
        viewModel.hasErrorSaving.observe(this) {
            if (it) {
                Toast.makeText(this, getString(R.string.error_saving_book), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.bookSaved.observe(this) {
            if (it != null) {
                val isInsert = id == 0L
                Log.d("RESULT", "Is insert sending $isInsert")
                val resultIntent = MainActivity.returnIntent(isInsert, it)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    companion object {
        fun detailIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(PARAM_ID, id)
            return intent
        }

        fun createIntent(context: Context): Intent {
            return Intent(context, BookDetailActivity::class.java)
        }

        private const val PARAM_ID = "id"

    }
}