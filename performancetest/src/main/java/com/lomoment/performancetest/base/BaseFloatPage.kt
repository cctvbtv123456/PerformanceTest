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

    var mTag: String? = null
    var mBundle: Bundle? = null

    private lateinit var mHandler: Handler
    lateinit var mRootView: View
    lateinit var mLayoutParams: WindowManager.LayoutParams


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

    open fun onLayoutParamsCreated(mLayoutParams: WindowManager.LayoutParams) {

    }

    open fun onViewCreated(mRootView: View) {

    }

    abstract fun onCreateView(mContext: Context, viewGroup: ViewGroup): View

    open fun onCreate(mContext: Context) {

    }

    open fun onDestroy() {

    }

    open fun onBackPress(): Boolean {
        return false
    }


    open fun getContext(): Context {
        return mRootView.context
    }

    open fun getResources(): Resources {
        return getContext().resources
    }

    open fun getString(@StringRes resId: Int): String {
        return getContext().getString(resId)
    }

    open fun isShow(): Boolean {
        return mRootView.isShown
    }

    fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

    open fun post(r: Runnable) {
        mHandler.post(r)
    }

    open fun postDelayed(r: Runnable, delayMillis: Long) {
        mHandler.postDelayed(r, delayMillis)
    }

    open fun runAfterRenderFinish(r: Runnable) {
        Looper.myQueue().addIdleHandler(object : MessageQueue.IdleHandler {
            override fun queueIdle(): Boolean {
                r.run()
                Looper.myQueue().removeIdleHandler(this)
                return false
            }
        })
    }

    open fun finish() {
        FloatPageManager.mInstance.remove(this)
    }

    open fun onEnterBackground() {
        mRootView.visibility = View.GONE
    }

    open fun onEnterForeground() {
        mRootView.visibility = View.VISIBLE
    }


}