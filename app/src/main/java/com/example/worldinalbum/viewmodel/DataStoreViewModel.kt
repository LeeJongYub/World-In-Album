package com.example.worldinalbum.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldinalbum.datastore.SaveIdDatastore
import kotlinx.coroutines.launch

class DataStoreViewModel : ViewModel() {

    fun saveIdVM(id : Int) = viewModelScope.launch {
        val saveId = SaveIdDatastore().saveIdData(id)
        Log.d("saveId", saveId.toString())
    }

    fun minusIdVM() = viewModelScope.launch {
        val minusIdVM = SaveIdDatastore().minusIdData()
        Log.d("minusId", minusIdVM.toString())
    }

    fun resetIdVM() = viewModelScope.launch {
        val resetId = SaveIdDatastore().resetIdData()
        Log.d("resetId", resetId.toString())
    }
}