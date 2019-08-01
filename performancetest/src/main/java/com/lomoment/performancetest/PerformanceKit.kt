package com.lomoment.performancetest

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.lomoment.performancetest.floatpage.FloatPageManager
import com.lomoment.performancetest.utils.LogInfoConfig

/**
 * @author panyz
 * @date 2019/8/1
 * @Description
 */
object PerformanceKit {

    fun install(app: Application) {

        app.registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{

            var startedActivityCount = 0

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity?) {
                startedActivityCount++
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityStopped(activity: Activity?) {
                startedActivityCount--
                if (startedActivityCount == 0) {
                    FloatPageManager.mInstance.finishAll()
                    LogInfoConfig.setLogInfoOpen(app, false)
                }
            }

            override fun onActivityDestroyed(activity: Activity?) {

            }
        })
    }

}