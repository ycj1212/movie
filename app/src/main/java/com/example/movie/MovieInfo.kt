package com.example.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
    val imageUrl: String,
    val title: String,
    val certification: String,
    val releaseDate: String,
    val genre: List<String>,
    val runTime: Int,
    val overview: String,
    val casts: List<Cast>
) : Parcelable
