package com.ilya.retrofit.navigation

sealed class Destination(val value: String) {
    object Posts : Destination("posts")
    object Todos : Destination("todos")
}