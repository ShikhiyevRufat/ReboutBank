package com.example.rebolutbank.features.account

import android.graphics.Color
import android.provider.CalendarContract.Colors
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardDesignViewModel @Inject constructor(): ViewModel() {

    var heightbtn = MutableLiveData<String>()
    var widthtbtn = MutableLiveData<String>()


    var blackbutton = MutableLiveData<Boolean>()

    var yellowbutton = MutableLiveData<Boolean>()

    var purplebutton = MutableLiveData<Boolean>()

    var pinkbutton = MutableLiveData<Boolean>()

    var lightbluebutton = MutableLiveData<Boolean>()

    fun blackbtn(){
        blackbutton.postValue(true)
    }

    fun yellowbtn(){
        yellowbutton.postValue(true)
    }

    fun purplebtn(){
        purplebutton.postValue(true)
    }

    fun pinkbtn(){
        pinkbutton.postValue(true)
    }

    fun lightbluebtn(){
        lightbluebutton.postValue(true)
    }



}