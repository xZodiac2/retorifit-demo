package com.ilya.retrofit.ui.state

import com.ilya.data.retrofit.Todo

sealed class TodosScreenState {
    object Error : TodosScreenState()
    object Waiting : TodosScreenState()
    data class Success(val todos: List<Todo>) : TodosScreenState()
}
