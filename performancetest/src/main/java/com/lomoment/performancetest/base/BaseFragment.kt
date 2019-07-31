package com.lomoment.performancetest.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * @author panyz
 * @date 2019/7/22
 * @Description
 */
open class BaseFragment : Fragment() {
    private var mRootView: View? = null
    private var mContainer: Int? = null

    @LayoutRes
     open fun onRequestLayout() :Int{
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = onRequestLayout()
        if (id > 0) {
            mRootView = inflater.inflate(id, container, false)
            return mRootView
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun onBackPress(): Boolean {
        return false
    }

    @SuppressLint("ShowToast")
    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG)
    }

    fun showContent(fragmentClass: Class<out BaseFragment>) {
        showContent(fragmentClass,null)
    }

    private fun showContent(fragmentClass: Class<out BaseFragment>, bundle: Bundle?) {
        (this.activity as BaseActivity?)?.showContent(fragmentClass, bundle)
    }

    fun finish() {
        (this.activity as BaseActivity?)?.doBack(this)
    }






}