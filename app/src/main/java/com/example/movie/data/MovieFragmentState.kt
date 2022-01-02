package com.example.movie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MovieFragmentState : Parcelable {
    POSTER, DETAILS, BOTH
}
