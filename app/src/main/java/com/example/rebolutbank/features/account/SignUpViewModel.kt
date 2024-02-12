package com.example.rebolutbank.features.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class SignUpViewModel @Inject constructor(): ViewModel() {

    var nextDate = MutableLiveData<Boolean>()
    var nameUser = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var country = MutableLiveData<String>()

    fun data(item: String) {
        nameUser.value = item
    }

    fun clickBtn(){
        nextDate.postValue(true)
    }

}