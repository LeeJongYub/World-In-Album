package com.example.worldinalbum.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveImage(myEntity: MyEntity)

    @Query("SELECT DISTINCT * FROM my_table WHERE selected = 1")
    fun getImage() : List<MyEntity>

    @Delete
    fun deleteImage(myEntity: MyEntity)

    @Query("DELETE FROM my_table")
    fun deleteAllImage()
}