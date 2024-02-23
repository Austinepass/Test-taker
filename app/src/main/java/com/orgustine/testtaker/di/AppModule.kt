package com.orgustine.testtaker.di

import com.google.ai.client.generativeai.GenerativeModel
import com.orgustine.testtaker.BuildConfig
import com.orgustine.testtaker.data.remote.QuizResponse
import com.orgustine.testtaker.util.MODEL_NAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGenerativeModel() = GenerativeModel(
        modelName = MODEL_NAME,
        apiKey = BuildConfig.API_KEY
    )

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesPracticeAdapter(moshi: Moshi) = moshi.adapter(QuizResponse::class.java)
}