package com.example.moviefy

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
//import com.example.moviefy.database.film

class FilmAdapter(
    private val onMovieClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private var listener: ((Movie) -> Unit)? = null
    private var deleteListener: ((Movie) -> Unit)? = null


    private var movies = arrayListOf<Movie>()

    inner class FilmViewHolder(v : View) : RecyclerView.ViewHolder(v){

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(movie : Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            itemView.setOnClickListener {
                onMovieClick.invoke(movie)

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Movie>){
        movies.clear()
        movies.addAll(newList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (Movie) -> Unit){
        this.listener = listener
    }

    fun setOnDeleteListener(deleteListener: (Movie) -> Unit) {
        this.deleteListener = deleteListener
    }
}
