package com.lomoment.performancetest.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Environment
import android.os.StatFs
import android.text.format.Formatter
import android.util.Log
import com.scottyab.rootbeer.RootBeer

/**
 * @author panyz
 * @date 2019/7/24
 * @Description
 */
object DevicesUtil {

    var ROOT: Boolean? = null


    /**
     * 获取包配置信息
     */
    fun getPackageInfo(context: Context?): PackageInfo? {
        val packageManager = context?.packageManager
        if (packageManager != null) {
            return packageManager.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
        }
        return null
    }

    /**
     * 获取SD卡空间
     */
    fun getSDCardSpace(context: Context?): String {
        return try {
            val sdAvailableSize = getSDAvailableSize(context)
            val sdTotalSize = getSDTotalSize(context)
            "$sdAvailableSize/$sdTotalSize"
        } catch (e: Exception) {
            "--/--"
        }
    }

    /**
     * 获取SD卡剩余可用空间
     */
    private fun getSDAvailableSize(context: Context?): String {
        val path = Environment.getExternalStorageDirectory()
        val statFs = StatFs(path.path)
        return Formatter.formatFileSize(context, statFs.blockSizeLong * statFs.availableBlocksLong)
    }

    /**
     * 获取SD卡总大小
     */
    private fun getSDTotalSize(context: Context?): String {
        val path = Environment.getExternalStorageDirectory()
        val statFs = StatFs(path.path)
        return Formatter.formatFileSize(context, statFs.blockSizeLong * statFs.blockCountLong)
    }

    /**
     * 获取机身内容大小
     */
    fun getRomSpace(context: Context?) :String{
        return try {
            val romAvailableSize = getRomAvailableSize(context)
            val romTotalSize = getRomTotalSize(context)
            "$romAvailableSize/$romTotalSize"
        } catch (e: Exception) {
            "--/--"
        }
    }

    /**
     * 获取机身可用内容
     */
    private fun getRomAvailableSize(context: Context?): String {
        val dataDirectory = Environment.getDataDirectory()
        val statFs = StatFs(dataDirectory.path)
        return Formatter.formatFileSize(context, statFs.blockSizeLong * statFs.availableBlocksLong)
    }

    /**
     * 获取机身总内存大小
     */
    private fun getRomTotalSize(context: Context?): String {
        val dataDirectory = Environment.getDataDirectory()
        val statFs = StatFs(dataDirectory.path)
        return Formatter.formatFileSize(context, statFs.blockSizeLong * statFs.blockCountLong)
    }

    fun isRoot(context: Context?): Boolean {
        if (ROOT != null) {
            return ROOT as Boolean
        }
        ROOT = try {
            val rootBeer = RootBeer(context)
            rootBeer.setLogging(true)
            rootBeer.isRootedWithoutBusyBoxCheck
        } catch (e: Exception) {
            Log.e("DevicesUtil", "Check root failed.", e)
            false
        }
        return ROOT as Boolean
    }

}