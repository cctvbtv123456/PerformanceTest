package com.lomoment.performancetest.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.lomoment.performancetest.Keys
import com.lomoment.performancetest.base.BaseActivity
import com.lomoment.performancetest.base.BaseFragment
import com.lomoment.performancetest.sysinfo.SysInfoFragment

class UniversalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle == null) {
            finish()
            return
        }

        val index = bundle.getInt(Keys.FRAGMENT_INDEX)
        var fragmentClass: Class<out BaseFragment>? = null

        when (index) {
            Keys.FRAGMENT_SYS_INFO -> {
                fragmentClass = SysInfoFragment::class.java
            }
            else -> {

            }
        }

        if (fragmentClass == null) {
            finish()
            return
        }

        showContent(fragmentClass, bundle)

    }
}
