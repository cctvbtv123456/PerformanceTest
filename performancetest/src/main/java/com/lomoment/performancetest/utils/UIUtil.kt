package com.lomoment.performancetest.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @author panyz
 * @date 2019/7/29
 * @Description
 */
object UIUtil {

    /**
     * 获取屏幕密度
     */
    fun getDensity(context: Context?): Float {
        val displayMetrics = DisplayMetrics()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.density
    }

    /**
     * 获取屏幕分辨率-宽
     */
    fun getWidthPixels(context: Context?): Int {
        val displayMetrics = DisplayMetrics()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * 获取屏幕分辨率-高
     */
    fun getHeightPixels(context: Context?): Int {
        return getRealHeightPixels(context) - getStatusBarHeight(context)
    }

    private fun getStatusBarHeight(context: Context?): Int {
        val id = context?.resources?.getIdentifier("status_bar_height", "dimen", "android")
        if (id != null) {
            return context.resources.getDimensionPixelSize(id)
        }
        return 0
    }


    private fun getRealHeightPixels(context: Context?): Int {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()

        val clazz = Class.forName("android.view.Display")
        val method = clazz.getMethod("getRealMetrics", DisplayMetrics::class.java)
        method.invoke(defaultDisplay, displayMetrics)
        return displayMetrics.heightPixels
    }

}