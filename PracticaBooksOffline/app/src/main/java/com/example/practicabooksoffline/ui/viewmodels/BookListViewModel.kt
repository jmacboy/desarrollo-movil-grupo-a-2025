package com.example.practicabooksoffline.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicabooksoffline.db.models.Book
import com.example.practicabooksoffline.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val _bookList: MutableLiveData<ArrayList<Book>> =
        MutableLiveData(arrayListOf())
    val bookList: MutableLiveData<ArrayList<Book>> = _bookList

    fun getBooks() {
        viewModelScope.launch {
            _bookList.postValue(
                bookRepository.getAllBooks()
            )
        }
    }
}