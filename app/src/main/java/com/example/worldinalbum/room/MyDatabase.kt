package com.example.worldinalbum.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    // Dao 또한 추상 메서드로 호출한다.
    abstract fun myDao(): MyDao

    // 싱글톤 패턴으로 Database 생성을 위한 instance를 만든다.
    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database",
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}