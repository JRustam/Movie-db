package jrustam.projects.movie.fragments.genre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import jrustam.projects.movie.R

class GenreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        Log.e("GenreActivity", "Bla bla bla")


        val id = intent.getIntExtra("id", 0)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, DiscoverFragment.getInstance(id)).commit()
    }
}
