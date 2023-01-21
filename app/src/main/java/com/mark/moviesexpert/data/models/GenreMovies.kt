package com.mark.moviesexpert.data.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreMovies(
    @Json(name = "page")
    @SerializedName("page"          ) var page         : Int?               = null,
    @Json(name = "results")
    @SerializedName("results"       ) var results      : List<Movie> = arrayListOf(),
    @Json(name = "total_pages")
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @Json(name = "total_results")
    @SerializedName("total_results" ) var totalResults : Int?               = null
)