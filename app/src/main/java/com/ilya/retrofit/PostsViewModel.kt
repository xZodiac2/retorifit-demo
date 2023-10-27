package com.ilya.retrofit

import androidx.lifecycle.ViewModel
import com.ilya.data.JsonPlaceholderRepository
import com.ilya.retrofit.ui.state.PostsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: JsonPlaceholderRepository,
) : ViewModel() {
    
    private val _postsScreenState = MutableStateFlow<PostsScreenState>(PostsScreenState.Loading)
    val postsScreenState = _postsScreenState.asStateFlow()
    
    suspend fun getAllPosts() = withContext(Dispatchers.IO) {
        _postsScreenState.value = PostsScreenState.Loading
        try {
            val posts = postsRepository.getAllPosts()
            _postsScreenState.value = PostsScreenState.Success(posts)
        } catch (e: Exception) {
            _postsScreenState.value = PostsScreenState.Error
        }
    }
    
}