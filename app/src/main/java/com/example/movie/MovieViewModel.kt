package com.example.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<List<MovieInfo>>()
    val movieList: LiveData<List<MovieInfo>> = _movieList

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = mutableListOf<MovieInfo>()
            val result = repository.getPopularMovies()

            result.results.forEach { movieResult ->
                val details = repository.getMovieDetails(movieResult.id)
                val releaseDates = repository.getMovieReleaseDates(movieResult.id)
                val credits = repository.getMovieCredits(movieResult.id)

                val certification = releaseDates.results.find { it.iso_3166_1 == "KR" }?.release_dates?.first()?.certification ?: ""
                val genres = mutableListOf<String>()
                for (i in 0 until 2) {
                    val genre = details.genres.getOrNull(i) ?: break
                    genres.add(genre.name)
                }

                movieList.add(MovieInfo(
                    imageUrl = "https://image.tmdb.org/t/p/original${movieResult.poster_path}",
                    title = movieResult.title,
                    certification = certification,
                    releaseDate = details.release_date,
                    genre = genres.toList(),
                    runTime = details.runtime,
                    overview = details.overview,
                    casts = credits.cast
                ))
            }

            withContext(Dispatchers.Main) {
                _movieList.value = movieList.toList()
            }
        }
    }
}
