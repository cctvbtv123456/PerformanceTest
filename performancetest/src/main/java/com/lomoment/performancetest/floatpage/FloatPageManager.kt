package com.lomoment.performancetest.floatpage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.view.WindowManager
import com.lomoment.performancetest.base.BaseFloatPage
import java.util.ArrayList

/**
 * @author panyz
 * @date 2019/7/31
 * @Description
 */
class FloatPageManager private constructor(){

    companion object{
        @SuppressLint("StaticFieldLeak")
        val mInstance = Holder.INSTANCE
    }

    private var windowManager: WindowManager? = null
    private lateinit var mContext: Context
    private val mPages = ArrayList<BaseFloatPage>()
    private val mListeners = ArrayList<FloatPageManagerListener>()


    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val  INSTANCE = FloatPageManager()
    }


    fun init(context: Context) {
        mContext = context.applicationContext
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    fun add(pageIntent: PageIntent) {
        if (pageIntent.targetClass == null) {
            return
        }

        val target = pageIntent.targetClass
        if (pageIntent.mode == PageIntent.MODE_SINGLE_INSTANCE) {
            for (mPage in mPages) {
                if (target?.isInstance(mPage)!!) {
                    return
                }
            }
        }

        val page = target?.newInstance() as BaseFloatPage
        page.mBundle = pageIntent.bundle
        page.mTag = pageIntent.tag

        mPages.add(page)

        page.performCreate(mContext)
        windowManager?.addView(page.mRootView, page.mLayoutParams)
        for (mListener in mListeners) {
            mListener.onPageAdd(page)
        }
    }

    fun remove(tag: String) {
        if (tag.isBlank()) {
            return
        }
        for (mPage in mPages) {
            if (tag == mPage.mTag) {
                windowManager?.removeView(mPage.mRootView)
                mPage.onDestroy()
                mPages.remove(mPage)
                return
            }
        }
    }

    fun remove(page: BaseFloatPage) {
        windowManager?.removeView(page.mRootView)
        page.onDestroy()
        mPages.remove(page)
    }

    fun removeAll() {
        val it = mPages.iterator()
        while (it.hasNext()) {
            val page = it.next()
            windowManager?.removeView(page.mRootView)
            page.onDestroy()
            it.remove()
        }
    }

    fun removeAll(pageClass: Class<out BaseFloatPage>) {
        val iterator = mPages.iterator()
        while (iterator.hasNext()) {
            val baseFloatPage = iterator.next()
            if (pageClass.isInstance(baseFloatPage)) {
                windowManager?.removeView(baseFloatPage.mRootView)
                baseFloatPage.onDestroy()
                iterator.remove()
            }
        }
    }

    fun getFloatPage(tag: String): BaseFloatPage ?{
        if (tag.isBlank()) {
            return null
        }
        for (mPage in mPages) {
            if (tag == mPage.mTag) {
                return mPage
            }
        }
        return null
    }

    fun notifyBackground() {
        for (mPage in mPages) {
            mPage.onEnterBackground()
        }
    }

    fun notifyForeground() {
        for (mPage in mPages) {
            mPage.onEnterForeground()
        }
    }

    fun addListener(listener: FloatPageManagerListener) {
        mListeners.add(listener)
    }

    fun removeListener(listener: FloatPageManagerListener) {
        mListeners.remove(listener)
    }

    fun finishAll() {
        for (mPage in mPages) {
            mPage.finish()
        }
    }

    interface FloatPageManagerListener{
        fun onPageAdd(page: BaseFloatPage)
    }

}

