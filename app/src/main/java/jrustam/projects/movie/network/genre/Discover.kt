package jrustam.projects.movie.network.genre


data class Discover(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int,
    val movies: List<jrustam.projects.movie.network.movie.ResultX>
)