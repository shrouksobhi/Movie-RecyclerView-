package com.shrouk.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R
import com.shrouk.movieapp.movieModel.Details
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    lateinit var itemid: TextView
    lateinit var databody:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        itemid = findViewById(R.id.itemid)
        databody=findViewById(R.id.databody)
        val msgid= intent.getIntExtra("productId" , 0)

        val retrofit = RetrofitFactory().apiInterface()
        retrofit.getId(msgid).enqueue(object : Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                when(response.code()){
                    200 -> {
                        Log.i("DetailsResponse" , "${response.body()?.data?.get(0)?.id}")
                        itemid.text = "Product Id : "+ response.body()?.data?.get(0)?.id
                    }

                    else -> {
                        val error = response.errorBody()?.string()
                        Toast.makeText(this@DetailsActivity , "$error" ,Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Toast.makeText(this@DetailsActivity , "${t.message}" ,Toast.LENGTH_SHORT).show()
            }

        })

     //   retrofit.getProductData().enqueue(
       //     object :Callback<Data>{
         //       override fun onResponse(call: Call<Data>, response: Response<Data>) {
           //         when(response.code()){
             //           200-> databody.text=response.body().toString()
               //         else->databody.text=response.errorBody().toString()
                 //   }
                //}

                //override fun onFailure(call: Call<Data>, t: Throwable) {
                 //databody.text=t.message.toString()                }

            //})



    }

    override fun onBackPressed() {
        startActivity(Intent(this , MainActivity::class.java))
    }

}
