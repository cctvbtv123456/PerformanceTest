package com.lomoment.performancetest.floatpage

import android.os.Bundle
import com.lomoment.performancetest.base.BaseFloatPage

/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
class PageIntent(var targetClass: Class<out BaseFloatPage>?) {
    companion object{
        const val MODE_NORMAL = 0
        const val MODE_SINGLE_INSTANCE = 1
    }

    lateinit var bundle: Bundle

    lateinit var tag: String

    var mode = MODE_NORMAL

}