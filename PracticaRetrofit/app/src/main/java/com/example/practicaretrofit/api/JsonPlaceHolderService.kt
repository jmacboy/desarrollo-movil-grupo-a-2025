package com.example.practicaretrofit.api

import com.example.practicaretrofit.api.dto.Post
import com.example.practicaretrofit.api.dto.Posts
import retrofit2.http.GET

interface JsonPlaceHolderService {
    @GET("posts")
    suspend fun getPosts(): Posts

    @GET("posts/{id}")
    suspend fun getPostById(id: Int): Post
}