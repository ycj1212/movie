package com.example.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MovieFragmentState : Parcelable {
    POSTER, DETAILS, BOTH
}
