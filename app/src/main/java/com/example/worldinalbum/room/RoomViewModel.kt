package com.example.worldinalbum.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    val roomRepository = RoomRepository()

    lateinit var getImageList : LiveData<List<MyEntity>>

    lateinit var myEntityss : MyEntity

    fun saveImagesVM(myEntity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.saveImages(myEntity)
        Log.d("myEntity1", roomRepository.saveImages(myEntity).toString())
        Log.d("myEntity2", myEntity.toString())

        myEntityss = myEntity
        val myE = myEntity
    }

//    fun getImagesVM() = viewModelScope.launch(Dispatchers.IO) {
//        val getImagesVM = roomRepository.getImages().asLiveData()
//
//        myEntityss
//        getImageList = getImagesVM
//
//        Log.d("myEntity3", getImagesVM.toString())
//    }

}