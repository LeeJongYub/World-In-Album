package com.example.worldinalbum.room

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
    }

    fun deleteImageVM(myEntity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {

        roomRepository.deleteImages(myEntity)
    }

    fun deleteAllImageVM() = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.deletAllImages()
    }

    fun getImagesVM() = viewModelScope.launch(Dispatchers.IO) {
        _roomSaveImageLiveData.postValue(roomRepository.getImages())

    }


}