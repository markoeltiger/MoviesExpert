package com.mark.moviesexpert.data.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class SingleMovieResponse(
    @Json(name = "adult")

    @SerializedName("adult") var adult: Boolean? = null,
    @Json(name = "backdrop_path")

    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @Json(name = "belongs_to_collection")

    @SerializedName("belongs_to_collection") var belongsToCollection: BelongsToCollection? = null,
    @Json(name = "id")

    @SerializedName("id") var id: Int? = null,
    @Json(name = "budget")

    @SerializedName("budget") var budget: Int? = null,
    @Json(name = "genres")

    @SerializedName("genres") var genres: List<Genre> = arrayListOf(),
    @Json(name = "homepage")

    @SerializedName("homepage") var homepage: String? = null,
    @Json(name = "imdb_id")

    @SerializedName("imdb_id") var imdbId: String? = null,
    @Json(name = "original_language")

    @SerializedName("original_language") var originalLanguage: String? = null,
    @Json(name = "original_title")

    @SerializedName("original_title") var originalTitle: String? = null,
    @Json(name = "overview")

    @SerializedName("overview") var overview: String? = null,
    @Json(name = "popularity")

    @SerializedName("popularity") var popularity: Double? = null,
    @Json(name = "poster_path")

    @SerializedName("poster_path") var posterPath: String? = null,
    @Json(name = "production_companies")

    @SerializedName("production_companies") var productionCompanies: List<ProductionCompany> = arrayListOf(),
    @Json(name = "production_countries")

    @SerializedName("production_countries") var productionCountries: List<ProductionCountry> = arrayListOf(),
    @Json(name = "release_date")

    @SerializedName("release_date") var releaseDate: String? = null,
    @Json(name = "revenue")

    @SerializedName("revenue") var revenue: Int? = null,
    @Json(name = "runtime")

    @SerializedName("runtime") var runtime: Int? = null,
    @Json(name = "spoken_languages")

    @SerializedName("spoken_languages") var spokenLanguages: List<SpokenLanguage> = arrayListOf(),
    @Json(name = "status")

    @SerializedName("status") var status: String? = null,
    @Json(name = "tagline")

    @SerializedName("tagline") var tagline: String? = null,
    @Json(name = "title")

    @SerializedName("title") var title: String? = null,

    @Json(name = "video")

    @SerializedName("video") var video: Boolean? = null,
    @Json(name = "vote_average")

    @SerializedName("vote_average") var voteAverage: Double? = null,
    @Json(name = "vote_count")

    @SerializedName("vote_count") var voteCount: Int? = null
)