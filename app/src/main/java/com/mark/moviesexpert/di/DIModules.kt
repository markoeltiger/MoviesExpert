package com.mark.moviesexpert.di

import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.repository.MovieDetailsRepository
import com.mark.moviesexpert.utils.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DIModules {

    @Singleton
    @Provides
    fun provideRetrofit(): MoviesInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_API_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MoviesInterface::class.java)
    }

    @Provides
    fun provideRepository(movieInterface: MoviesInterface): MovieDetailsRepository {
        return MovieDetailsRepository(movieInterface)
    }
}