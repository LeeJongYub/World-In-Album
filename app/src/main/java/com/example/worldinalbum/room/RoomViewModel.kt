package com.example.worldinalbum.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    val roomRepository = RoomRepository()

    private var _roomSaveImageLiveData = MutableLiveData<List<MyEntity>>()
    val roomSaveImageLiveData : LiveData<List<MyEntity>> get() = _roomSaveImageLiveData

    fun saveImagesVM(myEntity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.saveImages(myEntity)

        Log.d("saveImage", roomRepository.saveImages(myEntity).toString())
    }

    fun deleteImageVM(myEntity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.deleteImages(myEntity)

        Log.d("deleteImage", roomRepository.deleteImages(myEntity).toString())
    }

    fun deleteAllImageVM() = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.deletAllImages()

        Log.d("deleteAllImage", roomRepository.deletAllImages().toString())
    }

    fun getImagesVM() = viewModelScope.launch(Dispatchers.IO) {

//        val getImagesVM = roomRepository.getImages()
//        Log.d("getImage", getImagesVM.toString())
//
//        for (items in getImagesVM) {
//            val urls = items.thumbnailUrl
//            Log.d("getImageUrls", urls)
//
//        }

        _roomSaveImageLiveData.postValue(roomRepository.getImages())

    }

}