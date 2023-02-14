package com.example.worldinalbum.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveImage(myEntity: MyEntity)

//    @Query("SELECT * FROM my_table")
//    fun getImage() : Flow<List<MyEntity>>
}