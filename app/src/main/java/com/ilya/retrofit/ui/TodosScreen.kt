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
import com.ilya.retrofit.TodosViewModel
import com.ilya.retrofit.ui.state.TodosScreenState
import kotlinx.coroutines.launch

@Composable
fun TodosScreen(todosViewModel: TodosViewModel) {
    val state = todosViewModel.todosScreenState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state.value) {
            is TodosScreenState.Error -> item {
                TodosScreenOnError(onRetryClick = {
                    coroutineScope.launch { todosViewModel.getAllTodos() }
                })
            }
            
            is TodosScreenState.Waiting -> {
                coroutineScope.launch { todosViewModel.getAllTodos() }
                item { CircularProgressIndicator() }
            }
            
            is TodosScreenState.Success -> items((state.value as TodosScreenState.Success).todos) { Todo(it) }
        }
    }
}


@Composable
fun TodosScreenOnError(onRetryClick: () -> Unit) {
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