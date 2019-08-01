package com.lomoment.performancetest

import android.app.Application

/**
 * @author panyz
 * @date 2019/8/1
 * @Description
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PerformanceKit.install(this)
    }
}