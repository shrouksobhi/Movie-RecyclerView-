package com.shrouk.movieapp.movieModel

import com.google.gson.annotations.SerializedName

data class Details
    (
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("count")
    val count: Int,

    )


data class DetailsData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("restaurant_id")
    val restaurant_id: Int,
    @SerializedName("image")
    val image: String,

    )
