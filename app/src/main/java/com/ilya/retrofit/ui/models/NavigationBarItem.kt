package com.ilya.retrofit.ui.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.IconCompat.IconType
import com.ilya.retrofit.R
import com.ilya.retrofit.navigation.Destination

sealed class NavigationBarItem(
    val destination: Destination,
    val label: String,
    @IconType val iconRes: Int,
) {
    
    val painter: Painter
        @Composable get() = painterResource(iconRes)
    
    object PostsItem : NavigationBarItem(Destination.Posts, "Posts", R.drawable.ic_post)
    object TodosItem : NavigationBarItem(Destination.Todos, "Todos", R.drawable.ic_todo)
}
