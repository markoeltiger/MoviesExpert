package com.mark.moviesexpert.di

import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.utils.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DIModules {

    @Provides
    fun provideRetrofit(): MoviesInterface{
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build().create(MoviesInterface::class.java)

    }
}