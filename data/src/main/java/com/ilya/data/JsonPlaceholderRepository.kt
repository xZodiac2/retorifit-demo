package com.ilya.data

import com.ilya.data.retrofit.JsonPlaceholderApi
import com.ilya.data.retrofit.Post
import com.ilya.data.retrofit.Todo
import javax.inject.Inject

class JsonPlaceholderRepository @Inject internal constructor(
    private val api: JsonPlaceholderApi,
) {
    
    suspend fun getAllTodos(): List<Todo> {
        return api.getAllTodos()
    }
    
    suspend fun getAllPosts(): List<Post> {
        return api.getAllPosts()
    }
    
}