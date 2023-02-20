package com.example.worldinalbum.room

import com.example.worldinalbum.constants.MyApp

class RoomRepository {

    val db = MyDatabase.getInstance(MyApp.instance)

    fun saveImages(myEntity: MyEntity) = db.myDao().saveImage(myEntity)

    fun getImages() = db.myDao().getImage()

    fun deleteImages(myEntity: MyEntity) = db.myDao().deleteImage(myEntity)

    fun deletAllImages() = db.myDao().deleteAllImage()
}