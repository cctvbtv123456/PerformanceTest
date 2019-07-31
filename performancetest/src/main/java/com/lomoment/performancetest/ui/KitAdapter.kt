package com.lomoment.performancetest.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lomoment.performancetest.IKit
import com.lomoment.performancetest.R

/**
 * @author panyz
 * @date 2019/7/24
 * @Description
 */
class KitAdapter(items: List<IKit>, context: Context) : RecyclerView.Adapter<KitAdapter.KitViewHolder>() {

    private var list: List<IKit>? = items
    var context: Context? = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kit, parent, false)
        return KitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(viewHolder: KitViewHolder, position: Int) {
        val item = list?.get(position)
        if (item != null) {
            viewHolder.textName.setText(item.getName())
            viewHolder.icon.setImageResource(item.getIcon())

            viewHolder.itemView.setOnClickListener {
                item.onClick(context)
            }
        }

    }

    class KitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.tv_name)
        var icon: ImageView = itemView.findViewById(R.id.iv_icon)
    }

}