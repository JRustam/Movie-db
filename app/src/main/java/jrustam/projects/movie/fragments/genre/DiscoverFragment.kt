package jrustam.projects.movie.fragments.genre

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jrustam.projects.movie.JsonPlaceHolder
import jrustam.projects.movie.R
import jrustam.projects.movie.network.genre.Discover
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscoverFragment : Fragment(R.layout.fragment_discover) {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API = "0488eff6f8270bc1491491b67a251b90"

        fun getInstance(id: Int): DiscoverFragment {
            val discoverFragment = DiscoverFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            discoverFragment.arguments = bundle
            return discoverFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idKey = arguments!!.getInt("id")

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val json = retrofit.create(JsonPlaceHolder::class.java)
        val call = json.getDiscover(apiKey = API, genreId = idKey)

        Log.e("Discover Fragment", "Bla bla bla")

        call.enqueue(object : Callback<Discover> {

            override fun onResponse(call: Call<Discover>, response: Response<Discover>) {
                val result = response.body()!!.results
                recyclerView.adapter = DiscoverRecycler(result)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Discover>, t: Throwable) {
                Log.d("DiscoverFragment", "${t.message}")
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}