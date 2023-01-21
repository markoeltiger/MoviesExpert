package com.mark.moviesexpert

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.data.models.SingleMovieResponse
import com.mark.moviesexpert.repository.MovieDetailsRepository
import com.mark.moviesexpert.ui.movie.MoviePaging
import com.mark.moviesexpert.utils.Constants
import com.mark.moviesexpert.utils.Events
import com.mark.moviesexpert.utils.Result
import com.mark.moviesexpert.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MoviesInterface
, private val repository: MovieDetailsRepository
) : ViewModel() {


    private val query = MutableLiveData<String>()

    val list = query.switchMap { query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query,movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }
    private val _movieDetails = MutableLiveData<Events<com.mark.moviesexpert.utils.Result<SingleMovieResponse>>>()
    val movieDetails: LiveData<Events<com.mark.moviesexpert.utils.Result<SingleMovieResponse>>> = _movieDetails


    fun getMovieDetails(Id: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(com.mark.moviesexpert.utils.Result(Status.LOADING,null,null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(Id)))
//       movieInterface.getSingleMovie(Id,Constants.API_KEY,"en-US")
    }

}