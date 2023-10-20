package com.ilya.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ilya.retrofit.navigation.Destination
import com.ilya.retrofit.navigation.composable
import com.ilya.retrofit.navigation.navigate
import com.ilya.retrofit.ui.PostScreen
import com.ilya.retrofit.ui.TodosScreen
import com.ilya.retrofit.ui.models.NavigationBarItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val postsViewModel by viewModels<PostsViewModel>()
    private val todosViewModel by viewModels<TodosViewModel>()
    
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            
            val bottomBarItems = listOf(NavigationBarItem.PostsItem, NavigationBarItem.TodosItem)
            
            Scaffold(bottomBar = {
                BottomAppBar {
                    bottomBarItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == item.destination.value } == true,
                            onClick = { navController.navigate(item.destination) },
                            icon = { Icon(item.painter, contentDescription = null) },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = Destination.Posts.value,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(Destination.Posts) { PostScreen(postsViewModel) }
                    composable(Destination.Todos) { TodosScreen(todosViewModel) }
                }
            }
        }
    }
}

