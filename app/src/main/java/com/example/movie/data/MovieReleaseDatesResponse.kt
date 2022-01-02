package com.example.movie.data

data class MovieReleaseDatesResponse(
    val id: Int,
    val results: List<MovieReleaseDatesResult>
)

data class MovieReleaseDatesResult(
    val iso_3166_1: String,
    val release_dates: List<ReleaseDate>
)

data class ReleaseDate(
    val certification: String,
    val iso_639_1: String,
    val note: String,
    val release_date: String,
    val type: Int
)
