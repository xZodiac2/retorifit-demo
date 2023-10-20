package com.ilya.retrofit.ui.models

import com.ilya.retrofit.navigation.Destination

sealed class NavigationBarItem(
    val destination: Destination,
    val label: String,
) {
    object PostsItem : NavigationBarItem(
        Destination.Posts,
        "Posts"
    )
    
    object TodosItem : NavigationBarItem(
        Destination.Todos,
        "Todos"
    )
}
