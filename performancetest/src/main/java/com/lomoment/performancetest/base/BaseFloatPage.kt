package com.lomoment.performancetest.base

import android.content.Context
import android.content.res.Resources
import android.graphics.PixelFormat
import android.os.*
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.view.*
import android.widget.FrameLayout
import com.lomoment.performancetest.floatpage.FloatPageManager

/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
abstract class BaseFloatPage {

    private val TAG = "BaseFloatPage"

    lateinit var mTag: String
    lateinit var mBundle: Bundle
    private lateinit var mHandler: Handler
    private lateinit var mRootView: View
    private lateinit var mLayoutParams: WindowManager.LayoutParams


    fun performCreate(mContext: Context) {


        mHandler = Handler(Looper.myLooper())
        onCreate(mContext)
        mRootView = object : FrameLayout(mContext) {
            override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
                if (event != null) {
                    if (event.action == KeyEvent.ACTION_UP) {
                        if (event.keyCode == KeyEvent.KEYCODE_BACK || event.keyCode == KeyEvent.KEYCODE_HOME) {
                            return onBackPress()
                        }
                    }
                }
                return super.dispatchKeyEvent(event)
            }
        }

        val view = onCreateView(mContext, mRootView as ViewGroup)
        (mRootView as ViewGroup).addView(view)
        onViewCreated(mRootView)

        mLayoutParams = WindowManager.LayoutParams()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE
        }

        mLayoutParams.format = PixelFormat.TRANSPARENT
        mLayoutParams.gravity = Gravity.LEFT or Gravity.TOP
        onLayoutParamsCreated(mLayoutParams)

    }

    fun onLayoutParamsCreated(mLayoutParams: WindowManager.LayoutParams) {

    }

    fun onViewCreated(mRootView: View) {

    }

    abstract fun onCreateView(mContext: Context, viewGroup: ViewGroup): View

    fun onCreate(mContext: Context) {

    }

    fun onDestroy() {

    }

    fun onBackPress(): Boolean {
        return false
    }


    fun getContext(): Context {
        return mRootView.context
    }

    fun getResources(): Resources {
        return getContext().resources
    }

    fun getString(@StringRes resId:Int): String {
        return getContext().getString(resId)
    }

    fun isShow(): Boolean {
        return mRootView.isShown
    }

    fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

    fun post(r :Runnable) {
        mHandler.post(r)
    }

    fun postDelayed(r: Runnable, delayMillis: Long) {
        mHandler.postDelayed(r, delayMillis)
    }

    fun runAfterRenderFinish(r: Runnable) {
        Looper.myQueue().addIdleHandler(object : MessageQueue.IdleHandler {
            override fun queueIdle(): Boolean {
                r.run()
                Looper.myQueue().removeIdleHandler(this)
                return false
            }
        })
    }

    fun finish() {
        FloatPageManager.mInstance.remove(this)
    }

    fun onEnterBackground() {

    }

    fun onEnterForeground() {

    }


}