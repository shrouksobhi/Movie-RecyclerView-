package com.shrouk.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.movieapp.movieAdapter.MoviesAdapter
import com.shrouk.movieapp.movieModel.Movies

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclviewid)
        val moviedata = ArrayList<Movies>()

        recyclerview.layoutManager=GridLayoutManager(this,3)
        val movieadapter=MoviesAdapter(moviedata)
        recyclerview.adapter=movieadapter
        moviedata.add(Movies("The Matrix", "Comedy",(2021)))
        moviedata.add(Movies("Scream ","Crime Drama",(2022)))
        moviedata.add(Movies("Cyrano ","Crime Drama",(2021)))
        moviedata.add(Movies("Death on the Nile ","Comedy ",(2022)))
        moviedata.add(Movies("Amazing Maurice ","Crime Drama",(2022)))
        moviedata.add(Movies("Harry poter ", "Comedy",(2021)))
        moviedata.add(Movies("Hunger Game ", " Crime Drama",(2000)))
        moviedata.add(Movies("Home alone ", "Comedy ",(2022)))
        moviedata.add(Movies("Iron Man ", " Crime Drama",(2021)))
        moviedata.add(Movies("Death on the Nile ","Comedy ",(2022)))
        moviedata.add(Movies("Amazing Maurice ","Crime Drama",(2022)))
        moviedata.add(Movies("Harry poter ", "Comedy",(2021)))
        moviedata.add(Movies("Hunger Game ", " Crime Drama",(2000)))
        moviedata.add(Movies("Home alone ", "Comedy ",(2022)))
        moviedata.add(Movies("Iron Man ", " Crime Drama",(2021)))
         movieadapter.notifyDataSetChanged()


    }
}