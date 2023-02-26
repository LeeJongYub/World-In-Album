package com.example.worldinalbum.room

import androidx.room.*

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveImage(myEntity: MyEntity)

    @Query("SELECT * FROM my_table WHERE selected = 1")
    fun getImage() : List<MyEntity>

    @Delete
    fun deleteImage(myEntity: MyEntity)

    @Query("DELETE FROM my_table")
    fun deleteAllImage()
}