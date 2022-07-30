package com.shrouk.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R
import com.shrouk.movieapp.interfaces.ProductsOnClick
import com.shrouk.movieapp.movieAdapter.MoviesAdapter
import com.shrouk.movieapp.movieModel.Data
import com.shrouk.movieapp.movieModel.Movies
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() , ProductsOnClick {
    lateinit var recyclerview: RecyclerView
    lateinit var movieadapter: MoviesAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var movies: ArrayList<Data> =  ArrayList()

        recyclerview = findViewById(R.id.recyclviewid)
        layoutManager = GridLayoutManager(this, 3)
        recyclerview.layoutManager = layoutManager
        movieadapter = MoviesAdapter(movies, this@MainActivity , this@MainActivity)
        recyclerview.adapter = movieadapter


        val retrofit = RetrofitFactory().apiInterface()
        val call = retrofit.getData()
        call.enqueue(object : retrofit2.Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                movies.addAll(response.body()?.data!!)
                movieadapter.notifyItemRangeInserted(movies.size , movies.size)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "no internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        })


    }

    override fun productClick(productId: Int) {
        Log.i("ProductId" , "$productId")
        startActivity(Intent(this , DetailsActivity::class.java).putExtra("productId" , productId))
    }
}
