package com.example.practicabooksoffline.api

import com.example.practicabooksoffline.api.dto.BookDto
import com.example.practicabooksoffline.api.dto.BookDtos
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LibraryService {
    @GET("libros")
    suspend fun getBooks(): BookDtos

    @GET("libros/{id}")
    suspend fun getBookById(@Path("id") id: Long): BookDto

    @POST("libros")
    suspend fun insertBook(@Body book: BookDto): BookDto

    @PUT("libros/{id}")
    suspend fun updateBook(@Path("id") id: Long, @Body book: BookDto): BookDto

    @DELETE("libros/{id}")
    suspend fun deleteBook(@Path("id") id: Long)
}