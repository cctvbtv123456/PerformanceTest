package com.lomoment.performancetest.sysinfo


import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.lomoment.performancetest.R
import com.lomoment.performancetest.base.BaseFragment
import com.lomoment.performancetest.utils.DevicesUtil
import com.lomoment.performancetest.utils.UIUtil
import kotlinx.android.synthetic.main.fragment_sys_info.*


class SysInfoFragment : BaseFragment() {


    override fun onRequestLayout(): Int {
        return R.layout.fragment_sys_info
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_back.setOnClickListener { finish() }
        initData()
    }

    private fun initData() {
        val sysInfoItems = ArrayList<SysInfoItem>()
        addAppData(sysInfoItems)
        addDevicesData(sysInfoItems)

        rv_info.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_info.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        rv_info.adapter = SysInfoAdapter(sysInfoItems,context)


    }

    /**
     * 添加App信息
     */
    private fun addAppData(sysInfoItems:ArrayList<SysInfoItem>) {
        val packageInfo = DevicesUtil.getPackageInfo(context)
        if (packageInfo != null) {
            sysInfoItems.add(SysInfoItem("App信息", null))
            sysInfoItems.add(SysInfoItem("包名", packageInfo.packageName))
            sysInfoItems.add(SysInfoItem("应用版本名", packageInfo.versionName))
            sysInfoItems.add(SysInfoItem("应用版本号", packageInfo.versionCode.toString()))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sysInfoItems.add(SysInfoItem("最低系统版本号", context?.applicationInfo?.minSdkVersion.toString()))
            }
            sysInfoItems.add(SysInfoItem("目标系统版本号", context?.applicationInfo?.targetSdkVersion.toString()))
        }
    }

    /**
     * 添加设备信息
     */
    private fun addDevicesData(sysInfoItems: ArrayList<SysInfoItem>) {
        sysInfoItems.add(SysInfoItem("设备信息", null))
        sysInfoItems.add(SysInfoItem("设备型号", "${Build.MANUFACTURER} ${Build.MODEL}"))
        sysInfoItems.add(SysInfoItem("系统版本", "${Build.VERSION.RELEASE}(${Build.VERSION.SDK_INT})"))
        sysInfoItems.add(SysInfoItem("SD卡剩余空间", DevicesUtil.getSDCardSpace(context)))
        sysInfoItems.add(SysInfoItem("系统剩余空间", DevicesUtil.getRomSpace(context)))
        sysInfoItems.add(SysInfoItem("ROOT", DevicesUtil.isRoot(context).toString()))
        sysInfoItems.add(SysInfoItem("DENSITY(密度)", UIUtil.getDensity(context).toString()))
        sysInfoItems.add(SysInfoItem("分辨率", "${UIUtil.getWidthPixels(context)}x${UIUtil.getHeightPixels(context)}"))

    }

}
