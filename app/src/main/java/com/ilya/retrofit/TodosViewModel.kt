package com.ilya.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.data.JsonPlaceholderRepository
import com.ilya.data.retrofit.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todosRepository: JsonPlaceholderRepository,
) : ViewModel() {
    
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos
    
    suspend fun getAllTodos() = withContext(Dispatchers.IO) {
        val todos = todosRepository.getAllTodos()
        withContext(Dispatchers.Main) {
            _todos.postValue(todos)
        }
    }
    
}