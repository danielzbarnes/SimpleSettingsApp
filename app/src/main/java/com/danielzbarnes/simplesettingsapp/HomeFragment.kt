package com.danielzbarnes.simplesettingsapp

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager

class HomeFragment : Fragment() {

    private lateinit var fragmentLayout: ConstraintLayout
    private lateinit var aliasTextView: TextView
    private lateinit var notificationTextView: TextView
    private lateinit var prefOneTextView: TextView
    private lateinit var prefTwoTextView: TextView

    private lateinit var settingsButton: Button

    private lateinit var sharedPrefs: SharedPreferences

    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewModel(sharedPrefs)::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = requireContext().getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        fragmentLayout = view.findViewById(R.id.home_frag_layout)

        aliasTextView = view.findViewById(R.id.alias)
        notificationTextView = view.findViewById(R.id.notification)
        prefOneTextView = view.findViewById(R.id.pref_one)
        prefTwoTextView = view.findViewById(R.id.pref_two)

        settingsButton = view.findViewById(R.id.settings_button)

        settingsButton.setOnClickListener {
            view.findNavController().navigate(R.id.settingsFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUi()
    }

    private fun updateUi(){

        aliasTextView.text = sharedPrefs.getString("alias", "Default value")
        notificationTextView.text = sharedPrefs.getBoolean("notifications", false).toString()
        prefOneTextView.text = sharedPrefs.getBoolean("prefOne", false).toString()
        prefTwoTextView.text = sharedPrefs.getBoolean("prefTwo", false).toString()

    }

}