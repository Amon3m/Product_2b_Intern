package com.example.a2b.model

import android.content.Context
import android.content.SharedPreferences
class PreferenceManager private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var INSTANCE: PreferenceManager? = null

        fun getInstance(context: Context): PreferenceManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    var username: String?
        get() = sharedPreferences.getString("username", null)
        set(value) = sharedPreferences.edit().putString("username", value).apply()

    var email: String?
        get() = sharedPreferences.getString("email", null)
        set(value) = sharedPreferences.edit().putString("email", value).apply()

    var password: String?
        get() = sharedPreferences.getString("password", null)
        set(value) = sharedPreferences.edit().putString("password", value).apply()

    var phoneNumber: String?
        get() = sharedPreferences.getString("phone_number", null)
        set(value) = sharedPreferences.edit().putString("phone_number", value).apply()
}
