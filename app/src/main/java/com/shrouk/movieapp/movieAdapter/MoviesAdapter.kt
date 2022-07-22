package com.shrouk.movieapp.movieAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.movieapp.movieModel.Movies
import com.shrouk.movieapp.R

class MoviesAdapter ( private val movielist : List<Movies>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_items , parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = movielist[position]
        holder.title.text=movies.title
        holder.genre.text=movies.genre
        holder.year.text= movies.year.toString()
    }

    override fun getItemCount(): Int {
        return movielist.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var  title: TextView = itemView.findViewById(R.id.title)
        var genre:TextView = itemView.findViewById(R.id.genre)
        var year : TextView = itemView.findViewById(R.id.year)


    }

}