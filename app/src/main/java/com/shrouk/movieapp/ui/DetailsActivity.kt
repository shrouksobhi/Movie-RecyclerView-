package com.shrouk.movieapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R

class DetailsActivity : AppCompatActivity() {
    lateinit var itemid: TextView
    lateinit var databody:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        itemid = findViewById(R.id.itemid)
        val msgid= intent.getStringExtra("dataId")
       itemid.text= "Item ID: $msgid"

        val retrofit = RetrofitFactory().apiInterface()


    }


}
