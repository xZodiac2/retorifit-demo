package com.ilya.retrofit.ui.state

import com.ilya.data.retrofit.Post


sealed class PostsScreenState {
    object Error : PostsScreenState()
    object Waiting : PostsScreenState()
    data class Success(val postsList: List<Post>) : PostsScreenState()
}