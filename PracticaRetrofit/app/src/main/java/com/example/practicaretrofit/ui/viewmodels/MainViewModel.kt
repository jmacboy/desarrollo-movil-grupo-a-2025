package com.example.practicaretrofit.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaretrofit.api.dto.Posts
import com.example.practicaretrofit.repositories.PostRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _postList: MutableLiveData<Posts> =
        MutableLiveData(arrayListOf())
    val postList: LiveData<Posts> = _postList

    fun getPostList() {
        viewModelScope.launch {
            val response = PostRepository.getAllPosts()
            _postList.postValue(response)
        }
    }
}