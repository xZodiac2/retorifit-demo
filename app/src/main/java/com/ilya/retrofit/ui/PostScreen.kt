package com.ilya.retrofit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ilya.retrofit.PostsViewModel
import com.ilya.retrofit.ui.state.PostsScreenState
import kotlinx.coroutines.launch

@Composable
fun PostScreen(postsViewModel: PostsViewModel) {
    val state = postsViewModel.postsScreenState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state.value) {
            is PostsScreenState.Error -> item {
                PostScreenOnError(onRetryClick = {
                    coroutineScope.launch { postsViewModel.getAllPosts() }
                })
            }
            
            is PostsScreenState.Waiting -> {
                coroutineScope.launch { postsViewModel.getAllPosts() }
                item { CircularProgressIndicator() }
            }
            
            is PostsScreenState.Success -> items((state.value as PostsScreenState.Success).postsList) { Post(it) }
        }
    }
}

@Composable
fun PostScreenOnError(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unable to get posts")
        Button(onClick = onRetryClick) {
            Text(text = "Try again")
        }
    }
}