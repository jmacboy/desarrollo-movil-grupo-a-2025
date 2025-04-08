package com.example.practicaapicrud.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.repositories.BookRepository
import kotlinx.coroutines.launch

class BookDetailViewModel : ViewModel() {
    private val _book: MutableLiveData<Book> = MutableLiveData(null)
    val book: LiveData<Book> = _book

    private val _hasErrorSaving: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasErrorSaving: LiveData<Boolean> = _hasErrorSaving

    private val _bookSaved: MutableLiveData<Book> = MutableLiveData(null)
    val bookSaved: LiveData<Book> = _bookSaved

    fun saveBook(book: Book) {
        viewModelScope.launch {
            try {
                val theBook = BookRepository.saveBook(book)
                _bookSaved.postValue(theBook)
            } catch (e: Exception) {
                e.printStackTrace()
                _hasErrorSaving.postValue(true)
            }
        }
    }

    fun loadBook(id: Long) {
        viewModelScope.launch {
            val theBook = BookRepository.getBookById(id)
            _book.postValue(theBook)
        }
    }



}