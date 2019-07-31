package com.lomoment.performancetest.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lomoment.performancetest.R
import java.util.*

open class BaseActivity : AppCompatActivity() {

    private val mFragments = ArrayDeque<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun doBack(fragment: BaseFragment) {
        if (mFragments.contains(fragment)) {
            mFragments.remove(fragment)
            supportFragmentManager.popBackStack()
            if (mFragments.isEmpty()) {
                finish()
            }
        }
    }

    fun showContent(target: Class<out BaseFragment>) {
        showContent(target,null)
    }

    fun showContent(target: Class<out BaseFragment>, bundle: Bundle?) {
        val fragment = target.newInstance()
        if (bundle != null) {
            fragment.arguments = bundle
        }
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(android.R.id.content, fragment)
        mFragments.push(fragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (mFragments.isNotEmpty()) {
            val fragment = mFragments.first
            if (!fragment.onBackPress()) {
                mFragments.removeFirst()
                super.onBackPressed()
                if (mFragments.isEmpty()) {
                    finish()
                }
            }
        } else {
            super.onBackPressed()
        }
    }



}
