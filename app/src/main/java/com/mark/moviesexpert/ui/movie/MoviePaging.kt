package com.mark.moviesexpert.ui.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.data.models.Movie
import com.mark.moviesexpert.utils.Constants
import retrofit2.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviePaging (val s : String , val moviesInterface: MoviesInterface):PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key?:TMDB_STARTING_PAGE_INDEX
     return try {
                val  data = moviesInterface.getAllGenerMovies(Constants.API_KEY,"28",pageIndex)
         val movies = data.body()!!.results
         val nextKey =
             if (movies.isEmpty()) {
                 null
             } else {

                 pageIndex + (params.loadSize / 25)
             }
         LoadResult.Page(
             data = movies,
             prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
             nextKey = nextKey
         )

     } catch (exception: IOException) {
         return LoadResult.Error(exception)
     } catch (exception: HttpException) {
         return LoadResult.Error(exception)
     }
    }
}