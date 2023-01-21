package com.mark.moviesexpert

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mark.moviesexpert.data.Remote.MoviesInterface
import com.mark.moviesexpert.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MoviesInterface) : ViewModel() {


    private val query = MutableLiveData<String>()

    val list = query.switchMap { query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query,movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

}