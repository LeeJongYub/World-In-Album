package com.example.worldinalbum.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class MyEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "thumbnail_url")
    var thumbnailUrl : String,
    @ColumnInfo(name = "selected")
    var selected : Boolean = false
)
