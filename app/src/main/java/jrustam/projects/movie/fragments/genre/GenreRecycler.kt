package jrustam.projects.movie.fragments.genre

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import jrustam.projects.movie.R
import jrustam.projects.movie.network.genre.Result
import kotlinx.android.synthetic.main.genre_recycler.view.*

class GenreRecycler(private val list: List<Result>) : RecyclerView.Adapter<GenreRecycler.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.name.setOnClickListener {
            val intent = Intent(it.context, GenreActivity::class.java)
            intent.putExtra("id", list[position].id)
            startActivity(it.context, intent, null)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
    }
}
