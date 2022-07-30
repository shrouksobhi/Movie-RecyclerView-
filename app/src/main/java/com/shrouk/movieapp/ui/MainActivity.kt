package com.shrouk.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R
import com.shrouk.movieapp.movieAdapter.MoviesAdapter
import com.shrouk.movieapp.movieModel.Data
import com.shrouk.movieapp.movieModel.Movies
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerview: RecyclerView
    lateinit var movieadapter: MoviesAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var movies: Movies

        recyclerview = findViewById(R.id.recyclviewid)
        layoutManager = GridLayoutManager(this, 3)
        recyclerview.layoutManager = layoutManager

        val retrofit = RetrofitFactory().apiInterface()
        val call = retrofit.getData()
        call.enqueue(object : retrofit2.Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                movies = response.body()!!
                movieadapter = MoviesAdapter(movies, this@MainActivity)
                recyclerview.adapter = movieadapter

                movieadapter.setOnitemClick(object : MoviesAdapter.OnItemClickListner {
                    override fun onClick(position: Int) {
                        // Toast.makeText(applicationContext,"this $position",Toast.LENGTH_SHORT).show()

                        val callid = retrofit.getId(recyclerview.id)
                        callid.enqueue(object : retrofit2.Callback<Data> {
                            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                                if (response.code() == 200 || response.isSuccessful) {

                                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    val data = response.body().toString()
                                    intent.putExtra("dataId",data)

                                    startActivity(intent)

                                } else {
                                    val toastmsg = response.body().toString()
                                    Toast.makeText(applicationContext, toastmsg, Toast.LENGTH_SHORT).show()

                                }
                            }

                            override fun onFailure(call: Call<Data>, t: Throwable) {
                                Toast.makeText(applicationContext, "no internet", Toast.LENGTH_SHORT).show()
                            }

                        })

                    }
                })
                movieadapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "no internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        })


    }
}
