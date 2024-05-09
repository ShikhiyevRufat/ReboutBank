package com.example.rebolutbank

import android.content.Context

import android.content.SharedPreferences
import androidx.room.PrimaryKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface MySharedPreferencesInterface {
    fun savedstring(key: String, value: String)

    fun getString(key: String, defaultValue: String): String
}

class MySharedPrefecences @Inject constructor(@ApplicationContext private val context: Context) : MySharedPreferencesInterface{
    private val sharedPrefecences : SharedPreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    override fun savedstring(key: String, value: String) {
        sharedPrefecences.edit().putString(key,value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPrefecences.getString(key,defaultValue) ?: defaultValue
    }

}