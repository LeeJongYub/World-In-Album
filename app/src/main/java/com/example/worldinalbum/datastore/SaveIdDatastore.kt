package com.example.worldinalbum.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.worldinalbum.constants.MyApp

class SaveIdDatastore {

    companion object {
        val Context.saveIdDatastore: DataStore<Preferences> by preferencesDataStore("save_id_data")
    }

    val saveIdValueDataStore = MyApp.instance.saveIdDatastore

    val saveIdKey = intPreferencesKey("save_id_key")


    // idData 저장용
    suspend fun saveIdData(id : Int) {
        saveIdValueDataStore.edit { mutablePreferences ->

            mutablePreferences[saveIdKey] = id
            Log.d("iddUp", mutablePreferences[saveIdKey].toString())
        }
    }

    // 저장한 idData 리셋, 하트를 다시 눌러 빈하트가 되었을 때, 값을 빼줘야하기 때문
    suspend fun minusIdData() {
        saveIdValueDataStore.edit { mutablePreferences ->

            mutablePreferences[saveIdKey] = (mutablePreferences[saveIdKey] ?: 0) - 1
        }
    }

    suspend fun resetIdData() {
        saveIdValueDataStore.edit { mutablePreferences ->

            mutablePreferences[saveIdKey] = 0
        }
    }

}