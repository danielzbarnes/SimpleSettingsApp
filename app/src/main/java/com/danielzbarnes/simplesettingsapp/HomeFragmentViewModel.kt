package com.danielzbarnes.simplesettingsapp

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel(private val sharedPrefs: SharedPreferences): ViewModel() {


    fun getPrefs(): LiveData<SharedPreferences>{
        return sharedPrefs as LiveData<SharedPreferences>
    }



}