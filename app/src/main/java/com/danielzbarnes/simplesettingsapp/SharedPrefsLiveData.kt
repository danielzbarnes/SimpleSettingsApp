package com.danielzbarnes.simplesettingsapp

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class SharedPrefsLiveData<T>(private val sharedPrefs: SharedPreferences,
                             private val key: String,
                             private val getPreferenceVal: () -> T):
        LiveData<T>(getPreferenceVal()), SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onActive() {
        sharedPrefs.registerOnSharedPreferenceChangeListener(this)
        updateIfChanged()
    }

    override fun onInactive() = sharedPrefs.unregisterOnSharedPreferenceChangeListener(this)

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == this.key || key == null){
            updateIfChanged()
        }
    }

    private fun updateIfChanged() = with(getPreferenceVal()) { if (value != this) value = this }

    fun SharedPreferences.liveData(key: String, default: Boolean): LiveData<Boolean> =
        SharedPrefsLiveData(this, key) { getBoolean(key, default) }

    fun SharedPreferences.liveData(key: String, default: String?): LiveData<String?> =
        SharedPrefsLiveData(this, key) { getString(key, default) }

}