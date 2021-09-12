package com.danielzbarnes.simplesettingsapp

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat(){

    private lateinit var notificationSwitch: SwitchPreferenceCompat
    private lateinit var aliasText: EditTextPreference
    private lateinit var prefCheckBoxOne: CheckBoxPreference
    private lateinit var prefCheckBoxTwo: CheckBoxPreference

    private lateinit var sharedPref: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        sharedPref = requireActivity().getSharedPreferences("MySharedPrefs", MODE_PRIVATE)

        notificationSwitch = preferenceManager.findPreference("notifications")!!
        aliasText = preferenceManager.findPreference("alias_preference")!!
        prefCheckBoxOne = preferenceManager.findPreference("check_box_preference_1")!!
        prefCheckBoxTwo = preferenceManager.findPreference("check_box_preference_2")!!

        sharedPref.edit(commit = true){
            putBoolean("notifications", notificationSwitch.isChecked)
            putString("alias", aliasText.text)
            putBoolean("prefOne", prefCheckBoxOne.isChecked)
            putBoolean("prefTwo", prefCheckBoxTwo.isChecked)
        }

        val button: Preference? = preferenceManager.findPreference("pref_button")
        val clearButton: Preference? = preferenceManager.findPreference("clear_pref_button")

        if (button != null) {
            button.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                Toast.makeText(activity, "Preference Button Clicked.", Toast.LENGTH_SHORT).show()
                true
            }
        }

        if (clearButton != null){

            clearButton.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                aliasText.text = "Default value"
                notificationSwitch.isChecked = false
                prefCheckBoxOne.isChecked = false
                prefCheckBoxTwo.isChecked = true
                sharedPref.edit().clear().commit()
            }
        }
    }
}