package com.shrouk.movieapp.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.movieModel.Data
import com.shrouk.movieapp.movieModel.Movies
import retrofit2.Call
import retrofit2.Response

class ProductsVM : ViewModel () {
    var productsMD: MutableLiveData<ArrayList<Data>> = MutableLiveData()
    val productsLiveData: LiveData<ArrayList<Data>>
        get() = productsMD

    var errorMD: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = errorMD
    fun getProducts() {
        val retrofit = RetrofitFactory().apiInterface()
        val call = retrofit.getData()
        call.enqueue(object : retrofit2.Callback<Movies> {

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                when(response.code()){
                    200->
                        productsMD.postValue(response.body()?.data!! as ArrayList<Data>?)
                    else ->
                        errorMD.postValue(response.errorBody()?.toString())
                }

            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                errorMD.postValue(t.message)
            }

        })
    }




}

