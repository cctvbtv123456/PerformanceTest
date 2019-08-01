package com.lomoment.performancetest.floatpage

import android.annotation.SuppressLint
import android.content.Context
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

        if (pageIntent.mode == PageIntent.MODE_SINGLE_INSTANCE) {
            for (mPage in mPages) {
                if (pageIntent.targetClass.isInstance(mPage)) {
                    return
                }
            }
        }

        val page = pageIntent.targetClass.newInstance() as BaseFloatPage
        page.mBundle = pageIntent.bundle
        page.mTag = pageIntent.tag

        mPages.add(page)

        page.performCreate(mContext)


    }



    fun addListener(listener: FloatPageManagerListener) {
        mListeners.add(listener)
    }

    fun removeListener(listener: FloatPageManagerListener) {
        mListeners.remove(listener)
    }

    fun remove(baseFloatPage: BaseFloatPage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface FloatPageManagerListener{
        fun onPageAdd(page: BaseFloatPage)
    }

}

