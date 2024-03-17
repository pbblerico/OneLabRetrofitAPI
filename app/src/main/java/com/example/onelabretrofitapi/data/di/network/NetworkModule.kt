package com.example.onelabretrofitapi.data.di.network

import com.example.onelabretrofitapi.data.api.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @CharacterUrl
    @Provides
    fun pictureUrl() = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideCharactersRetrofit(
        @CharacterUrl url: String, client: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCharactersApi(
        retrofit: Retrofit
    ): CharactersApi = retrofit.create(CharactersApi::class.java)
}

@Qualifier
annotation class CharacterUrl
