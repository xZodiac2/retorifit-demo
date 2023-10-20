package com.ilya.data.retrofit.di

import com.ilya.data.retrofit.JsonPlaceholderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
internal object RetrofitModule {
    @Provides
    fun provideApi(@Named("baseUrl") baseUrl: String): JsonPlaceholderApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        
        return retrofit.create(JsonPlaceholderApi::class.java)
    }
}