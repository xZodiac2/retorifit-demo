package com.ilya.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface JsonPlaceholderApi {
    
    @GET("/posts")
    suspend fun getAllPosts(): List<Post>
    
    @GET("/todos")
    suspend fun getAllTodos(): List<Todo>
    
}