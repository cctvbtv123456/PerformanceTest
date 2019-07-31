package com.lomoment.performancetest

import android.content.Context

/**
 * @author panyz
 * @date 2019/7/22
 * @Description
 */
interface IKit {

    fun getName(): Int

    fun getIcon(): Int

    fun onClick(context: Context?)

    fun onAppInit(context: Context)
}