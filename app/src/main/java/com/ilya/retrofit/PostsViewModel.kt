package com.ilya.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.data.JsonPlaceholderRepository
import com.ilya.data.retrofit.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: JsonPlaceholderRepository,
) : ViewModel() {
    
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts
    
    suspend fun getAllPosts() = withContext(Dispatchers.IO) {
        val posts = postsRepository.getAllPosts()
        withContext(Dispatchers.Main) {
            _posts.postValue(posts)
        }
    }
    
}