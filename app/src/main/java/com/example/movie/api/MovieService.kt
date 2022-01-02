package com.example.movie.api

import com.example.movie.*
import com.example.movie.data.MovieCreditsResponse
import com.example.movie.data.MovieDetailsResponse
import com.example.movie.data.MovieReleaseDatesResponse
import com.example.movie.data.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/latest")
    suspend fun getLatestMovies(
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "ko"
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "ko"
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/release_dates")
    suspend fun getMovieReleaseDates(
        @Path("movie_id") movieId: Int
    ): MovieReleaseDatesResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "ko"
    ): MovieCreditsResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): MovieService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val url = request.url.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                    val newRequest = request.newBuilder().url(url).build()

                    chain.proceed(newRequest)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }
}
