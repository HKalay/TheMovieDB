package com.kalay.themoviedb.core.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class LocalPrefManager(context: Context) {

    private val shared: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun getSharedPreferences() = this.shared

    fun push(key: String, value: String) {
        shared.edit { putString(key, value) }
    }

    fun push(key: String, value: Int) {
        shared.edit { putInt(key, value) }
    }

    fun push(key: String, value: Long) {
        shared.edit { putLong(key, value) }
    }

    fun push(key: String, value: Boolean) {
        shared.edit { putBoolean(key, value) }
    }

    fun push(key: String, value: Float) {
        shared.edit { putFloat(key, value) }
    }

    fun pull(key: String, defaultValue: String): String =
        shared.getString(key, defaultValue).toString()

    fun pull(key: String, defaultValue: Int): Int =
        shared.getInt(key, defaultValue)

    fun pull(key: String, defaultValue: Long): Long =
        shared.getLong(key, defaultValue)

    fun pull(key: String, defaultValue: Float): Float =
        shared.getFloat(key, defaultValue)

    fun pull(key: String, defaultValue: Boolean): Boolean =
        shared.getBoolean(key, defaultValue)

    fun getAllKeys(): List<String> {
        val keyList = arrayListOf<String>()
        val allEntries = shared.all
        allEntries?.forEach {
            keyList.add(it.key)
        }
        return keyList
    }

    fun remove(key: String) {
        shared.edit { remove(key) }
    }
}