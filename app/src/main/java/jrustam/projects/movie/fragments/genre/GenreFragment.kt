package jrustam.projects.movie.fragments.genre

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jrustam.projects.movie.JsonPlaceHolder
import jrustam.projects.movie.R
import jrustam.projects.movie.network.genre.Genre
import jrustam.projects.movie.network.genre.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenreFragment : Fragment(R.layout.genre_fragment) {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API = "0488eff6f8270bc1491491b67a251b90"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = GridLayoutManager(activity, 2)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolder = retrofit.create(JsonPlaceHolder::class.java)
        val call = jsonPlaceHolder.getGenresList(apiKey = API)
        call.enqueue(object : Callback<Genre> {

            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                val genre = response.body()!!.genres
                recycler.adapter = GenreRecycler(genre)
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Log.d("GenreFragment", "${t.message}")
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}