package com.lomoment.performancetest.sysinfo

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lomoment.performancetest.R

/**
 * @author panyz
 * @date 2019/7/29
 * @Description
 */
class SysInfoAdapter(items:List<SysInfoItem>, private var context: Context?) : RecyclerView.Adapter<SysInfoAdapter.SysInfoViewHolder>() {

    private var list: List<SysInfoItem>? = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SysInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sys_info, parent, false)
        return SysInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(viewHolder: SysInfoViewHolder, position: Int) {
        val infoItem = list?.get(position)
        if (infoItem != null) {
            viewHolder.tvName.text = infoItem.name
            if (infoItem.value != null) {
                viewHolder.tvValue.text = infoItem.value
            } else {
                viewHolder.tvName.setTextColor(ContextCompat.getColor(context!!,R.color.colorPrimary))
            }
        }
    }

    class SysInfoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_label)
        var tvValue :TextView = itemView.findViewById(R.id.tv_value)
    }

}