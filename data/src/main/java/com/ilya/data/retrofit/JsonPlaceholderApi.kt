package com.ilya.data.retrofit

import retrofit2.http.GET

internal interface JsonPlaceholderApi {
    
    @GET("/posts")
    suspend fun getAllPosts(): List<Post>
    
    @GET("/todos")
    suspend fun getAllTodos(): List<Todo>
    
}