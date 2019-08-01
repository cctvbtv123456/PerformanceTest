package com.lomoment.performancetest.loginfo

import android.app.AlertDialog
import android.content.Context
import com.lomoment.performancetest.IKit
import com.lomoment.performancetest.R
import com.lomoment.performancetest.floatpage.FloatPageManager
import com.lomoment.performancetest.floatpage.PageIntent
import com.lomoment.performancetest.utils.LogInfoConfig


/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
class LogInfo :IKit{
    override fun getName(): Int {
        return R.string.kit_loginfo
    }

    override fun getIcon(): Int {
        return R.drawable.ic_launcher_round
    }

    override fun onClick(context: Context?) {
        val logInfoOpen = LogInfoConfig.isLogInfoOpen(context)
        val msg: String
        msg = if (logInfoOpen!!) {
            "是否关闭日志信息?"
        } else {
            "是否打开日志信息?"
        }

        val builder = AlertDialog.Builder(context)
            .setTitle("日志信息")
            .setMessage(msg)
            .setPositiveButton("确定") { dialog, _ ->
                if (logInfoOpen) {
                    FloatPageManager.mInstance.removeAll(LogInfoFloatPage::class.java)
                } else {
                    val pageIntent = PageIntent(LogInfoFloatPage::class.java)
                    pageIntent.mode = PageIntent.MODE_SINGLE_INSTANCE
                    FloatPageManager.mInstance.add(pageIntent)
                }
                LogInfoConfig.setLogInfoOpen(context, !logInfoOpen)
                dialog.dismiss()
            }
            .setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    override fun onAppInit(context: Context) {
    }

}