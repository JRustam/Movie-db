package jrustam.projects.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import jrustam.projects.movie.fragments.exit.AlertDialogFragment
import jrustam.projects.movie.fragments.genre.GenreFragment
import jrustam.projects.movie.fragments.movie.HomeFragment

class MainActivity : AppCompatActivity() {

//    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

        when (menuItem.itemId) {
            R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                HomeFragment()).commit()
            R.id.genres -> supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                GenreFragment()).commit()
            R.id.exit -> {
                val alertDialog = AlertDialogFragment()
                alertDialog.show(supportFragmentManager, "AlertDialogFragment")
            }
        }
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!).commit()
        true
    }

    private fun openDialog() {
        val dialogFragment = DialogFragment()
        dialogFragment.show(supportFragmentManager, "AlertDialogFragment")
    }
}