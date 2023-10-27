package com.ilya.retrofit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.ilya.data.retrofit.Todo
import com.ilya.retrofit.TodosViewModel
import com.ilya.retrofit.ui.state.TodosScreenState
import kotlinx.coroutines.launch

@Composable
fun TodosScreen(todosViewModel: TodosViewModel) {
    val state = todosViewModel.todosScreenState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    
    when (state.value) {
        is TodosScreenState.Error -> {
            ErrorState(onRetryClick = {
                coroutineScope.launch { todosViewModel.getAllTodos() }
            })
        }
        
        is TodosScreenState.Loading -> {
            LoadingState(onStart = {
                coroutineScope.launch { todosViewModel.getAllTodos() }
            })
        }
        
        is TodosScreenState.Success -> SuccessState((state.value as TodosScreenState.Success).todos)
    }
}

@Composable
private fun ErrorState(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unable to get todos")
        Button(onClick = onRetryClick) {
            Text(text = "Try again")
        }
    }
}

@Composable
private fun LoadingState(onStart: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
        onStart()
    }
}

@Composable
private fun SuccessState(todos: List<Todo>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(todos) { Todo(todoData = it) }
    }
}