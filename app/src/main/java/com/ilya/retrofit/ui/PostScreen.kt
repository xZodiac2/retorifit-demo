package com.ilya.retrofit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ilya.retrofit.PostsViewModel

@Composable
fun PostScreen(postsViewModel: PostsViewModel) {
    val state = postsViewModel.posts.observeAsState()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.value?.forEach {
            item { Post(it) }
        } ?: item { CircularProgressIndicator() }
    }
    
    LaunchedEffect(key1 = Unit, block = {
        if (state.value == null) {
            postsViewModel.getAllPosts()
        }
    })
}