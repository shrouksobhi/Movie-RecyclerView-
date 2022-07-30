package com.shrouk.movieapp.movieModel

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("errors")
    val errors: Errors?,
)

data class Errors(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
)

