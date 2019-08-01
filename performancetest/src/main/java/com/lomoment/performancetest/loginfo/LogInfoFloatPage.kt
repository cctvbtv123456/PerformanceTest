package com.lomoment.performancetest.loginfo

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lomoment.performancetest.R
import com.lomoment.performancetest.base.BaseFloatPage

/**
 * @author panyz
 * @date 2019/8/1
 * @Description
 */
class LogInfoFloatPage : BaseFloatPage() {

    private lateinit var tvMiniLog:TextView
    private lateinit var layoutLogInfoContent:LinearLayout
    private lateinit var mWindowManager: WindowManager

    override fun onCreateView(mContext: Context, viewGroup: ViewGroup): View {
        return LayoutInflater.from(mContext).inflate(R.layout.float_page_log_info, null)
    }

    override fun onCreate(mContext: Context) {
        super.onCreate(mContext)
        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    override fun onViewCreated(mRootView: View) {
        super.onViewCreated(mRootView)
        initViews()
    }

    private fun initViews() {
        val ivHide = findViewById<ImageView>(R.id.iv_back)
        tvMiniLog = findViewById(R.id.tv_mini_log)
        layoutLogInfoContent = findViewById(R.id.layout_log_info)
        ivHide.setOnClickListener { hidePage() }
        tvMiniLog.setOnClickListener { showPage() }
    }

    private fun hidePage() {
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        mLayoutParams.gravity = Gravity.START or Gravity.TOP
        tvMiniLog.visibility = View.VISIBLE
        layoutLogInfoContent.visibility = View.GONE
        mWindowManager.updateViewLayout(mRootView,mLayoutParams)
    }

    private fun showPage() {
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        mLayoutParams.gravity = Gravity.START or Gravity.TOP
        tvMiniLog.visibility = View.GONE
        layoutLogInfoContent.visibility = View.VISIBLE
        mWindowManager.updateViewLayout(mRootView,mLayoutParams)
    }

    override fun onBackPress(): Boolean {
        hidePage()
        return true
    }


}