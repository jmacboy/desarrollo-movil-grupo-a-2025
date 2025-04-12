package com.example.practicabooksoffline.repositories

import com.example.practicabooksoffline.api.LibraryService
import com.example.practicabooksoffline.db.dao.BookDao
import com.example.practicabooksoffline.db.models.Book
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val dao: BookDao,
    private val api: LibraryService
) {
    suspend fun getAllBooks(): ArrayList<Book> {
        syncBooks()
        return dao.getAll() as ArrayList<Book>
    }

    private suspend fun syncBooks() {

        val books = api.getBooks()
        for (book in books) {
            if (dao.getById(book.id) != null) {
                dao.updateBook(book.toBookEntity())
            } else {
                dao.insertBook(book.toBookEntity())
            }
        }

    }
}