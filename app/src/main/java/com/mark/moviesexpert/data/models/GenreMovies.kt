package com.mark.moviesexpert.data.models

data class GenreMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)