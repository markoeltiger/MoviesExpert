package com.mark.moviesexpert.repository

import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.data.models.SingleMovieResponse
import com.mark.moviesexpert.utils.Constants
import com.mark.moviesexpert.utils.Result
import com.mark.moviesexpert.utils.Status

class MovieDetailsRepository(private val moviesInterface: MoviesInterface) {

suspend fun getMovieDetails(id:String):Result<SingleMovieResponse>{
    return try {

        val response = moviesInterface.getSingleMovie(id, Constants.API_KEY,"en-US")
        println("kxx${response}")
        Result(Status.SUCCESS, response, null)




    } catch (e: Exception) {
        println("kxx${e.toString()}")

        Result(Status.ERROR, null, null)
    }


}

}

