package com.example.practicaapicrud.api

import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.api.dto.Books
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LibraryService {
    @GET("libros")
    suspend fun getBooks(): Books

    @GET("libros/{id}")
    suspend fun getBookById(@Path("id") id: Long): Book

    @POST("libros")
    suspend fun insertBook(@Body book: Book): Book

    @PUT("libros/{id}")
    suspend fun updateBook(@Path("id") id: Long, @Body book: Book): Book

    @DELETE("libros/{id}")
    suspend fun deleteBook(@Path("id") id: Long)
}