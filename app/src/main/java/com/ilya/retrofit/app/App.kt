package com.ilya.retrofit.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com/"
    }
}