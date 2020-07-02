package jrustam.projects.movie.fragments.genre

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jrustam.projects.movie.R
import jrustam.projects.movie.network.genre.ResultX
import kotlinx.android.synthetic.main.discover_recycler.view.*

class DiscoverRecycler(private val list: List<ResultX>) : RecyclerView.Adapter<DiscoverRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.discover_recycler, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + list[position].poster_path)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent = Intent(it.context, DiscoverActivity::class.java)
            intent.putExtra("id", list[position].id)
            startActivity(it.context, intent, null)
        }

        holder.title.text = list[position].title
        holder.voteAverage.text = "Vote Average: ${list[position].vote_average}"
        holder.overview.text = list[position].overview
        holder.releaseDate.text = "Release Date: ${list[position].release_date}"
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title
        val voteAverage: TextView = itemView.voteAverage
        val overview: TextView = itemView.overview
        val releaseDate: TextView = itemView.releaseDate
        val imageView: ImageView = itemView.imageView
    }

}