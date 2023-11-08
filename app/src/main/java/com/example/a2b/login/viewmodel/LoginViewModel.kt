package com.example.a2b.login.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a2b.model.PreferenceManager

class LoginViewModel : ViewModel() {
    lateinit var preferenceManager: PreferenceManager


    fun signUp(context:Context,username:String,mail:String,password:String,phoneNum:String ){
        preferenceManager = PreferenceManager.getInstance(context)

        Log.e("splash","$username")
        preferenceManager.username=username
        val pref =preferenceManager.username
        Log.e("splash","pref $pref")

        preferenceManager.password=password
        preferenceManager.email=mail
        preferenceManager.phoneNumber=phoneNum
    }

}