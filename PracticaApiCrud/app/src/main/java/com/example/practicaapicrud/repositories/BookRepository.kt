package com.example.practicaapicrud.repositories

import com.example.practicaapicrud.api.LibraryService
import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.api.dto.Books

object BookRepository {
    suspend fun getAllBooks(): Books {
        return RetrofitRepository
            .getRetrofitInstance()
            .create(LibraryService::class.java)
            .getBooks()
    }

    private suspend fun insertBook(book: Book): Book {
        return RetrofitRepository
            .getRetrofitInstance()
            .create(LibraryService::class.java)
            .insertBook(book)
    }

    suspend fun saveBook(book: Book): Book {
        return if (book.id == 0L) {
            insertBook(book)
        } else {
            updateBook(book)
        }
    }

    private suspend fun updateBook(book: Book): Book {
        return RetrofitRepository
            .getRetrofitInstance()
            .create(LibraryService::class.java)
            .updateBook(book.id, book)
    }

    suspend fun getBookById(id: Long): Book {
        return RetrofitRepository
            .getRetrofitInstance()
            .create(LibraryService::class.java)
            .getBookById(id)
    }

    suspend fun deleteBook(id: Long) {
        RetrofitRepository
            .getRetrofitInstance()
            .create(LibraryService::class.java)
            .deleteBook(id)

    }
}