package com.example.movie.data

import com.example.movie.api.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    suspend fun getLatestMovies(): MovieResponse {
        return service.getLatestMovies()
    }

    suspend fun getNowPlayingMovies(): MovieResponse {
        return service.getNowPlayingMovies()
    }

    suspend fun getPopularMovies(): MovieResponse {
        return service.getPopularMovies()
    }

    suspend fun getTopRatedMovies(): MovieResponse {
        return service.getTopRatedMovies()
    }

    suspend fun getUpcomingMovies(): MovieResponse {
        return service.getUpcomingMovies()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        return service.getMovieDetails(movieId)
    }

    suspend fun getMovieReleaseDates(movieId: Int): MovieReleaseDatesResponse {
        return service.getMovieReleaseDates(movieId)
    }

    suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse {
        return service.getMovieCredits(movieId)
    }
}
