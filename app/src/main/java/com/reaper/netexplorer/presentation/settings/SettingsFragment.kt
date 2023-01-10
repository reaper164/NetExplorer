package com.reaper.netexplorer.presentation.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.reaper.netexplorer.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}