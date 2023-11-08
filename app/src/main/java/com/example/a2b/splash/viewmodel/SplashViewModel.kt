package com.example.a2b.splash.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a2b.model.PreferenceManager

class SplashViewModel : ViewModel() {
    lateinit var preferenceManager: PreferenceManager


    fun checkIfUserLoggedIn(context: Context): Boolean {
        preferenceManager = PreferenceManager.getInstance(context)
        val userName= preferenceManager.username
        Log.e("splash","$userName")
        return !preferenceManager.username.isNullOrEmpty()
    }

}