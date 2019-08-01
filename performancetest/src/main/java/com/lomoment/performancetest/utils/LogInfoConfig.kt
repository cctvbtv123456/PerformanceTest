package com.lomoment.performancetest.utils

import android.content.Context
import com.lomoment.performancetest.Keys

/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
object LogInfoConfig {
    fun isLogInfoOpen(context: Context?): Boolean? {
        return SharedPrefsUtil.getBoolean(context,Keys.LOG_INFO_OPEN,false)
    }

    fun setLogInfoOpen(context: Context?, open: Boolean) {
        SharedPrefsUtil.putBoolean(context, Keys.LOG_INFO_OPEN, open)
    }
}