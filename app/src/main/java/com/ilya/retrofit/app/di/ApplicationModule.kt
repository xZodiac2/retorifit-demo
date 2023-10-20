package com.ilya.retrofit.app.di

import com.ilya.retrofit.app.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ApplicationModule {
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return App.baseUrl
    }
}