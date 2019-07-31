package com.lomoment.performancetest.sysinfo

import android.content.Context
import android.content.Intent
import com.lomoment.performancetest.IKit
import com.lomoment.performancetest.Keys
import com.lomoment.performancetest.R
import com.lomoment.performancetest.ui.UniversalActivity

/**
 * @author panyz
 * @date 2019/7/22
 * @Description
 */
class SysInfo : IKit {
    override fun getName(): Int {
        return R.string.kit_sysinfo
    }

    override fun getIcon(): Int {
        return R.drawable.ic_launcher_round
    }

    override fun onClick(context: Context?) {
        val intent = Intent(context, UniversalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Keys.FRAGMENT_INDEX, Keys.FRAGMENT_SYS_INFO)
        context?.startActivity(intent)
    }

    override fun onAppInit(context: Context) {

    }
}