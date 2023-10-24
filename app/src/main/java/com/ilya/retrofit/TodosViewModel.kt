package com.ilya.retrofit

import androidx.lifecycle.ViewModel
import com.ilya.data.JsonPlaceholderRepository
import com.ilya.retrofit.ui.state.TodosScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todosRepository: JsonPlaceholderRepository,
) : ViewModel() {
    
    private val _todosScreenState = MutableStateFlow<TodosScreenState>(TodosScreenState.Waiting)
    val todosScreenState = _todosScreenState.asStateFlow()
    
    suspend fun getAllTodos() = withContext(Dispatchers.IO) {
        try {
            val todos = todosRepository.getAllTodos()
            _todosScreenState.value = TodosScreenState.Success(todos)
        } catch (e: Exception) {
            _todosScreenState.value = TodosScreenState.Error
        }
    }
    
}