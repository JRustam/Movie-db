package jrustam.projects.movie

import jrustam.projects.movie.network.genre.Discover
import jrustam.projects.movie.network.genre.Genre
import jrustam.projects.movie.network.movie.Movie
import jrustam.projects.movie.network.movie.MovieX
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolder {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String) : Call<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String) : Call<Movie>

    @GET("movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String) : Call<Movie>

    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String) : Call<MovieX>

    @GET("genre/movie/list")
    fun getGenresList(@Query("api_key") apiKey: String) : Call<Genre>

    @GET("discover/movie")
    fun getDiscover(@Query("api_key") apiKey: String, @Query("with_genres")  genreId: Int): Call<Discover>
}