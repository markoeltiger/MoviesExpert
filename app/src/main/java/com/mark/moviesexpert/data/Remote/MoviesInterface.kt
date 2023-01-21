package com.mark.moviesexpert.data.Remote

import com.mark.moviesexpert.data.models.GeneresResponse
import com.mark.moviesexpert.data.models.GenreMovies
import com.mark.moviesexpert.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesInterface {
    @GET()
    suspend fun getAllMovies(

    )
    @GET(Constants.GeneresEndPoint)
    suspend fun getAllMovieGeners(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ):GeneresResponse
    @GET(Constants.GenereMoviesEndPoint)
    suspend fun getAllGenerMovies(
        @Query("api_key") apiKey: String?,
        @Query("with_genres") genere: String?,
        @Query("page") page: Int?,
    ):Response<GenreMovies>
}