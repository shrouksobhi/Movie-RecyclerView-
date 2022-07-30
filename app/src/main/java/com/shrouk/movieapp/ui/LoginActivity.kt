package com.shrouk.movieapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shrouk.movieapp.Api.RetrofitFactory
import com.shrouk.movieapp.R
import com.shrouk.movieapp.Storage.SharedPrefManager
import com.shrouk.movieapp.movieModel.LoginResponse
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var memail: EditText
    private lateinit var mpassword: EditText
    private lateinit var btnsignin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        btnsignin.setOnClickListener {

            val email = memail.text.toString().trim()
            val password = mpassword.text.toString().trim()

            if (email.isEmpty()) {
                memail.error = "Email required"
                memail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                mpassword.error = "Password required"
                mpassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitFactory().apiInterface().userLogin(email, password)
                .enqueue(object : retrofit2.Callback<LoginResponse> {

                    override fun onResponse(
                        call: Call<LoginResponse>, response: Response<LoginResponse>
                    ) {

                        if (response.body()?.status == true) {

                            response.body()!!.errors?.let { it1 ->
                                SharedPrefManager.getInstance(applicationContext)
                                    .saveError(it1)
                            }

                            if (response.code() == 200) {
                                "${response.body()?.message}".toast(this@LoginActivity)
                            }

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)

                        } else {

                            "${response.body()?.errors}".toast(this@LoginActivity)
                            Log.d("Debuuuuuuuug", "${response.body()} ")


                        }

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        "${t.message}".toast(this@LoginActivity)
                    }

                })
        }
    }


    private fun initViews() {
        memail = findViewById(R.id.email)
        mpassword = findViewById(R.id.password)
        btnsignin = findViewById(R.id.signinbutton)

    }


    //Toast extension function..
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        return Toast.makeText(context, this.toString(), duration).show()
    }


    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }

    }
}


//  if (response.isSuccessful){
//      "Code: ${response.code()}".toast(this@LoginActivity)
//       intent=Intent(this@LoginActivity, MainActivity::class.java)
//       startActivity(intent)
//   }