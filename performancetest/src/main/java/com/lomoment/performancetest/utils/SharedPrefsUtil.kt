package com.lomoment.performancetest.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
object SharedPrefsUtil {

    private const val SHARED_PREFS_PERFORMANCE: String = "shared_prefs_performance"

     private fun getSharedPrefs(context: Context?): SharedPreferences? {
        return getSharedPrefs(context, SHARED_PREFS_PERFORMANCE)
    }

    public fun getSharedPrefs(context: Context?, name: String): SharedPreferences? {
        if (context != null) {
            return context.getSharedPreferences(name,Context.MODE_PRIVATE)
        }
        return null
    }

    fun getString(context: Context?, key: String, defVal: String):String {
        return getSharedPrefs(context)?.getString(key, defVal).toString()
    }

    fun putString(context: Context?, key: String, value: String) {
        putString(context, SHARED_PREFS_PERFORMANCE,key, value)
    }

    private fun putString(context: Context?, table: String, key: String, value: String) {
        getSharedPrefs(context,table)?.edit()?.putString(key,value)?.apply()
    }

    fun putBoolean(context: Context?, key: String, value: Boolean) {
        putBoolean(context, SHARED_PREFS_PERFORMANCE,key,value)
    }

    private fun putBoolean(context: Context?, table: String, key: String, value: Boolean) {
        getSharedPrefs(context,table)?.edit()?.putBoolean(key,value)?.apply()
    }

    fun getBoolean(context: Context?,key: String,defVal:Boolean): Boolean? {
        return SharedPrefsUtil.getSharedPrefs(context)?.getBoolean(key,defVal)
    }

    private fun putInt(context: Context?, key: String, value: Int) {
        putInt(context, SHARED_PREFS_PERFORMANCE,key,value)
    }

    fun putInt(context: Context?, table: String, key: String, value: Int) {
        getSharedPrefs(context)?.edit()?.putInt(key,value)?.apply()
    }

    fun getInt(context: Context?, key: String, defVal: Int): Int? {
        return getSharedPrefs(context)?.getInt(key,defVal)
    }



}