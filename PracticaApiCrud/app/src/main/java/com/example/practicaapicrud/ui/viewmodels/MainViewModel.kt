package com.example.practicaapicrud.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.api.dto.Books
import com.example.practicaapicrud.repositories.BookRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _bookList: MutableLiveData<Books> =
        MutableLiveData(arrayListOf())
    val bookList: LiveData<Books> = _bookList

    private val _bookDeleted: MutableLiveData<Book> = MutableLiveData(null)
    val bookDeleted: LiveData<Book> = _bookDeleted

    fun loadBooks() {
        viewModelScope.launch {
            val response = BookRepository.getAllBooks()
            _bookList.postValue(response)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {

            BookRepository.deleteBook(book.id)
            _bookDeleted.postValue(book)

        }
    }
}