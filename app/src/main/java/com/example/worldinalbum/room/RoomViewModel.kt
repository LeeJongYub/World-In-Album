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

    fun saveImagesVM(url : String) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.saveImages(MyEntity(0, url))

        Log.d("saveImage", roomRepository.saveImages(MyEntity(0, url)).toString())
    }

    fun getImagesVM() = viewModelScope.launch(Dispatchers.IO) {
        val getImagesVM = roomRepository.getImages()
        Log.d("getImage", getImagesVM.toString())

        for (items in getImagesVM) {
            val urls = items.thumbnailUrl
            Log.d("getImageUrls", urls)
        }

        _roomSaveImageLiveData.postValue(getImagesVM)

    }

}