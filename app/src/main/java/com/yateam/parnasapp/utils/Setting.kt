package com.yateam.parnasapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Vadim on 25.01.16.
 */
class Setting {
    companion object {
        private val ServerPreferencesName = "PARNAS_APP_PREFERENCE"
        public val PreferenceCookies = "COOKIES"

        fun getAppSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(ServerPreferencesName, 0)
        }

        fun setParam(param: String, value: String?, context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.putString(param, value)
            editor.apply()
        }

        fun getParam(param: String, context: Context): String? {
            return getAppSharedPreferences(context).getString(param, null)
        }

        fun setHashSet(param: String, value: Set<String>, context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.putStringSet(param, value)
            editor.apply()
        }

        fun getHashSet(param: String, context: Context): Set<String>? {
            return getAppSharedPreferences(context).getStringSet(param, null)
        }

        fun getBoolean(param: String, context: Context): Boolean {
            return getAppSharedPreferences(context).getBoolean(param, true)
        }

        fun setBoolean(param: String, b: Boolean, context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.putBoolean(param, b)
            editor.apply()
        }

        fun getInt(param: String, context: Context): Int {
            return getAppSharedPreferences(context).getInt(param, 200)
        }

        fun setInt(param: String, value: Int, context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.putInt(param, value)
            editor.apply()
        }

        fun remove(param: String, context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.remove(param)
            editor.apply()
        }

        fun clear(context: Context) {
            val editor = getAppSharedPreferences(context).edit()
            editor.clear()
            editor.apply()
        }
    }
}
