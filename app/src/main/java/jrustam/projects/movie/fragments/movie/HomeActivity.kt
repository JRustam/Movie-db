package jrustam.projects.movie.fragments.movie

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Transformations.map
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import jrustam.projects.movie.JsonPlaceHolder
import jrustam.projects.movie.R
import jrustam.projects.movie.network.movie.MovieX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : YouTubeBaseActivity() {

    companion object {
        private var key = ""
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API = "0488eff6f8270bc1491491b67a251b90"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        val intent = intent
        val id = intent.getIntExtra("id", 0)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val json = retrofit.create(JsonPlaceHolder::class.java)
        val call = json.getVideos(id, API)
        call.enqueue(object : Callback<MovieX> {

            override fun onResponse(call: Call<MovieX>, response: Response<MovieX>) {
                val result = response.body()!!.results

                for (res in result) {
                    key = res.key
                }

                getYoutubeVideo(key)
            }

            override fun onFailure(call: Call<MovieX>, t: Throwable) {
                Log.d("HomeActivity", "${t.message}")
            }
        })

        super.onResume()
    }

    private fun getYoutubeVideo(key: String) {
        val youtubePlayer = findViewById<YouTubePlayerView>(R.id.youtubePlayer)
        val initializer = object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                p1?.loadVideo(key)
                p1!!.setFullscreen(true)
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

            }
        }
        youtubePlayer.initialize("AIzaSyAg7L1qe9DWR5T3IOKFoiKQCwDKHe2Q2mc", initializer)
    }
}