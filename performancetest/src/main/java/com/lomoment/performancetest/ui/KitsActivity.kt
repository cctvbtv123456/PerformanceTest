package com.lomoment.performancetest.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.OrientationHelper
import com.lomoment.performancetest.R
import com.lomoment.performancetest.base.BaseActivity
import com.lomoment.performancetest.sysinfo.SysInfo
import kotlinx.android.synthetic.main.activity_performance.*

class KitsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performance)
        initAdapter()
    }

    private fun initAdapter() {

        val list = arrayListOf(SysInfo())
        val kitAdapter = KitAdapter(list, this)
        val gridLayoutManager = GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false)
        rv_kit.layoutManager = gridLayoutManager
        rv_kit.adapter = kitAdapter
    }
}
