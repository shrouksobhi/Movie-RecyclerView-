package com.shrouk.movieapp.MVP

import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.movieModel.Data
import com.shrouk.movieapp.movieModel.Movies
import retrofit2.Call
import retrofit2.Response

class ProductsPresenter (var productsview:ProductsView) {

    fun getProducts(){
      RetrofitFactory().apiInterface().getData()
       .enqueue(object :retrofit2.Callback<Movies>{
           override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
           if(response.code()==200){
               productsview.setProducts(response.body()?.data!! as ArrayList<Data>)
           }
               else{
                   productsview.setError(response.errorBody()!!.toString())
           }
           }

           override fun onFailure(call: Call<Movies>, t: Throwable) {
               productsview.setError(t.message.toString())
           }

       })
    }
}