package com.mark.moviesexpert.repository

import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.data.models.GeneresResponse
import com.mark.moviesexpert.data.models.SingleMovieResponse
import com.mark.moviesexpert.utils.Constants
import com.mark.moviesexpert.utils.Result
import com.mark.moviesexpert.utils.Status

class MovieDetailsRepository(private val moviesInterface: MoviesInterface) {
suspend fun getMoviesGenres():Result<GeneresResponse>{
    return try {
        val response = moviesInterface.getAllMovieGeners( Constants.API_KEY,"en-US")

        Result(Status.SUCCESS, response, null)
    } catch (e: Exception) {

        Result(Status.ERROR, null, null)
    }
}
suspend fun getMovieDetails(id:String):Result<SingleMovieResponse>{
    return try {

        val response = moviesInterface.getSingleMovie(id, Constants.API_KEY,"en-US")
        Result(Status.SUCCESS, response, null)




    } catch (e: Exception) {

        Result(Status.ERROR, null, null)
    }


}

}

