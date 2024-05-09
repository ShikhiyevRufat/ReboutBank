package com.example.rebolutbank.features.account

import androidx.lifecycle.ViewModel
import com.example.rebolutbank.MySharedPreferencesInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(val preferences : MySharedPreferencesInterface): ViewModel() {

    fun playIntroPassed(){
        preferences.savedstring("intro","1")
    }

    val isIntroPlayed : Boolean get(){
        return preferences.getString("intro","0") == "1"
    }
}