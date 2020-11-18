package com.udacity.shoestore.utils

import android.app.Activity
import android.content.Context

class PreferencesHelper {

    companion object {

        private const val USER_LOGGED_IN_KEY = "isLoggedIn"

        fun storeLoginToPreferences(activity: Activity, isLoggedIn: Boolean) {
            val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            sharedPreferences.edit()
                .putBoolean(USER_LOGGED_IN_KEY, isLoggedIn)
                .apply()
        }

        fun getLoginFromPreferences(activity: Activity): Boolean {
            val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(USER_LOGGED_IN_KEY, false)
        }
    }
}