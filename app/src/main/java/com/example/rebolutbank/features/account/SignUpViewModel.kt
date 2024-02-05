package com.example.rebolutbank.features.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    var nextDate = MutableLiveData<Boolean>()

    fun clickBtn(){
        nextDate.postValue(true)
    }

}