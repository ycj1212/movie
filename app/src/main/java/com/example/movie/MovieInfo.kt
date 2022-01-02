package com.example.movie

data class MovieInfo(
    val title: String,
    val certification: String,
    val releaseDate: String,
    val genre: List<String>,
    val runTime: Int,
    val overview: String,
    val casts: List<Cast>
)
