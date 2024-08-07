package com.solux.flory.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.solux.flory.data.datasource.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSourceImpl @Inject constructor
    (
    private val dataStore: DataStore<Preferences>
) : UserPreferencesDataSource {
    private val USER_ACCESSTOKEN = stringPreferencesKey("user_accesstoken")
    private val CHECK_LOGIN = booleanPreferencesKey("check_login")

    override suspend fun saveUserAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[USER_ACCESSTOKEN] = accessToken
        }
    }

    override fun getUserAccessToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ACCESSTOKEN]
    }

    override suspend fun saveCheckLogin(checkLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[CHECK_LOGIN] = checkLogin
        }
    }

    override fun getCheckLogin(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[CHECK_LOGIN] ?: false
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ACCESSTOKEN)
            preferences.remove(CHECK_LOGIN)
        }
    }
}