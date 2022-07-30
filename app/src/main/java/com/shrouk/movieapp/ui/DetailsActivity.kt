package com.shrouk.movieapp.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R
import com.shrouk.movieapp.movieModel.Data
import retrofit2.Call
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    lateinit var itemid: TextView
    lateinit var databody:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        itemid = findViewById(R.id.itemid)
        val msgid= intent.getStringExtra("userId")
       itemid.text= "Item ID: $msgid"

        val retrofit = RetrofitFactory().apiInterface()

        val call = retrofit.getId(0) //msaid
        call.enqueue(object : retrofit2.Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.code() == 200 || response.isSuccessful) {
                  databody.text= response.body().toString()

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


}
