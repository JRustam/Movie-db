package jrustam.projects.movie.fragments.movie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jrustam.projects.movie.JsonPlaceHolder
import jrustam.projects.movie.R
import jrustam.projects.movie.network.movie.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object {
        private const val baseUrl = "https://api.themoviedb.org/3/"
        private const val apiKey = "0488eff6f8270bc1491491b67a251b90"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.movie_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.popular -> {
                getPopularMovies()
                Toast.makeText(context, "Popular", Toast.LENGTH_LONG).show()
            }
            R.id.topRated -> {
                getTopRatedMovies()
                Toast.makeText(context, "Top Rated", Toast.LENGTH_LONG).show()
            }
            R.id.upComing -> {
                getUpComingMovies()
                Toast.makeText(context, "Up Rated", Toast.LENGTH_LONG).show()
            }
         }

        return super.onOptionsItemSelected(item)
    }

    private fun getPopularMovies() {
        val recyclerView= view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jsonPlaceHolder= retrofit.create(JsonPlaceHolder::class.java)
        val call= jsonPlaceHolder.getPopularMovies(apiKey = apiKey)

        call.enqueue(object: Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val moviesResult= response.body()!!.results
                recyclerView.adapter =
                    HomeRecycler(moviesResult)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("HomeFragment", "${t.message}")
            }
        })
    }

    private fun getTopRatedMovies() {
        val recyclerView= view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jsonPlaceHolder= retrofit.create(JsonPlaceHolder::class.java)
        val call= jsonPlaceHolder.getTopRatedMovies(apiKey = apiKey)

        call.enqueue(object: Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val moviesResult= response.body()!!.results
                recyclerView.adapter = HomeRecycler(moviesResult)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("HomeFragment", "${t.message}")
            }
        })
    }

    private fun getUpComingMovies() {
        val recyclerView= view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jsonPlaceHolder= retrofit.create(JsonPlaceHolder::class.java)
        val call= jsonPlaceHolder.getUpComingMovies(apiKey = apiKey)

        call.enqueue(object: Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val moviesResult= response.body()!!.results
                recyclerView.adapter =
                    HomeRecycler(
                        moviesResult
                    )
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("HomeFragment", "${t.message}")
            }
        })
    }
}