package com.example.worldinalbum.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class MyEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo("thumbnail_url")
    var thumbnailUrl : String,
    @ColumnInfo("selected")
    var selected : Boolean
)
