package com.example.practicabooksoffline.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicabooksoffline.db.models.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM Book")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getById(id: Int): Book?

    @Insert
    suspend fun insertBook(person: Book): Long

    @Update
    suspend fun updateBook(person: Book)

    @Delete
    suspend fun deleteBook(person: Book): Int
}