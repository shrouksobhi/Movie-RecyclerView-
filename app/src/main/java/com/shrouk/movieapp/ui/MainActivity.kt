package com.shrouk.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.movieapp.MVP.ProductsPresenter
import com.shrouk.movieapp.MVP.ProductsView
import com.shrouk.movieapp.MVVM.ProductsVM
import com.shrouk.movieapp.R
import com.shrouk.movieapp.interfaces.ProductsOnClick
import com.shrouk.movieapp.movieAdapter.MoviesAdapter
import com.shrouk.movieapp.movieModel.Data

class MainActivity : AppCompatActivity() , ProductsOnClick ,ProductsView {
    lateinit var recyclerview: RecyclerView
    lateinit var movieadapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var movies: ArrayList<Data>
    val  productvm : ProductsVM by viewModels()
   lateinit var productspresenter:ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        //*******MVP BLOCK *********
        productspresenter= ProductsPresenter(this)
        productspresenter.getProducts()

        //**************

        // *********MVVM block of code **********
        // productvm.getProducts()
        //  productvm.productsLiveData.observe(this, Observer {
        //      movies=it
        //     installViews()
        //      movieadapter.notifyItemRangeInserted(movies.size , movies.size)
        //   })
        //  productvm.errorLiveData.observe(this, Observer {
        //      Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        //})
        //***************** End of MVVM block*************


        //******* CODE BELOW FOR USING RETROFIT TP GET PRODUCTS (NO ARCH WERE PATTERN USED)*******

        //   val retrofit = RetrofitFactory().apiInterface()
        // val call = retrofit.getData()
        //call.enqueue(object : retrofit2.Callback<Movies> {
        //  override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
        //    movies.addAll(response.body()?.data!!)
        //  movieadapter.notifyItemRangeInserted(movies.size , movies.size)

        // override fun onFailure(call: Call<Movies>, t: Throwable) {
       //     Toast.makeText(this@MainActivity, "no internet connection", Toast.LENGTH_SHORT)
       //         .show()
       //  }
      //  })
        //*********END OF RETROFIT BLOCK*********


    }

  fun initView(){
      recyclerview = findViewById(R.id.recyclviewid)

  }
    fun installViews(){
        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        movieadapter = MoviesAdapter(movies, this@MainActivity , this@MainActivity)
        recyclerview.adapter = movieadapter
    }

    override fun onPause() {
        super.onPause()
        productvm.productsLiveData.removeObservers(this)
        productvm.errorLiveData.removeObservers(this)
    }
    override fun productClick(productId: Int) {
        Log.i("ProductId" , "$productId")
        startActivity(Intent(this , DetailsActivity::class.java).putExtra("productId" , productId))
    }

    override fun setProducts(datalist: ArrayList<Data>) {
        movies=datalist
        installViews()
    }

    override fun setError(errormessage: String) {
    Toast.makeText(this,errormessage,Toast.LENGTH_SHORT).show()    }

}
