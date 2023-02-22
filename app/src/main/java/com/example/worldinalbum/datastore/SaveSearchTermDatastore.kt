package com.example.worldinalbum.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.worldinalbum.constants.MyApp

class SaveSearchTermDatastore {

    companion object {
        val Context.saveSearchTermDatastore: DataStore<Preferences> by preferencesDataStore("save_search_term_data")
    }

    val saveSearchTermValueDatastore = MyApp.instance.saveSearchTermDatastore

    val saveSearchTermKey = stringPreferencesKey("save_search_term_key")

    suspend fun saveSearchTermData(searchTerm : String) {
        saveSearchTermValueDatastore.edit { mutablePreferences ->
            mutablePreferences[saveSearchTermKey] = searchTerm
        }
    }

    suspend fun deleteSearchTermData(searchTerm: String) {
        saveSearchTermValueDatastore.edit { mutablePreferences ->
            mutablePreferences[saveSearchTermKey] = ""
        }
    }
}