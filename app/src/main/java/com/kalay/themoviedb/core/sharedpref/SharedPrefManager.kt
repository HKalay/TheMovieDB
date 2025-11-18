package com.kalay.themoviedb.core.sharedpref

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)
    private val sharedPreferences = localPrefManager.getSharedPreferences()

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    var isAppFirstOpen: Boolean
        get() = localPrefManager.pull(
            key = SharedPrefKey.APP_FIRST_OPEN.name,
            defaultValue = true
        )
        set(value) = localPrefManager.push(
            key = SharedPrefKey.APP_FIRST_OPEN.name,
            value = value
        )

}

