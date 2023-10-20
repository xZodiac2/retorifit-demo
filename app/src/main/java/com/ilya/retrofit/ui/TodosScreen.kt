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
import com.ilya.retrofit.TodosViewModel

@Composable
fun TodosScreen(todosViewModel: TodosViewModel) {
    val state = todosViewModel.todos.observeAsState()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.value?.forEach {
            item { Todo(todoData = it) }
        } ?: item { CircularProgressIndicator() }
    }
    
    LaunchedEffect(key1 = Unit, block = {
        if (state.value == null) {
            todosViewModel.getAllTodos()
        }
    })
    
}