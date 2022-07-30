package com.shrouk.movieapp.Storage

import android.content.Context
import com.shrouk.movieapp.movieModel.Errors

class SharedPrefManager private constructor(private val mCtx: Context) {
    private val SHARED_PREF_NAME = "my_shared_preff"
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: Errors
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Errors(
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("password", null),

                )
        }


    fun saveError(user: Errors) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("email", user.email)
        editor.putString("password", user.password)

        editor.apply()

    }


    companion object {

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            var mInstance: SharedPrefManager? = null
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance
        }
    }
}
