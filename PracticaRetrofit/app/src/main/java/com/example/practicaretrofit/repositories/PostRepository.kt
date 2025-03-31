package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.JsonPlaceHolderService
import com.example.practicaretrofit.api.dto.Posts

object PostRepository {
    suspend fun getAllPosts(): Posts {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JsonPlaceHolderService::class.java)
        return service.getPosts()
    }
}