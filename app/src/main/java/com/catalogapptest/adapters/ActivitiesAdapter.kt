package com.catalogapptest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catalogapptest.databinding.ItemActivityListBinding
import com.catalogapptest.model.Activity
import com.catalogapptest.ui.listeners.ItemListener
import java.util.*


class ActivitiesAdapter(private val mContext: Context, mList: ArrayList<Activity>, private val listener: ItemListener) :
    RecyclerView.Adapter<ActivitiesAdapter.CustomViewHolder>() {

    private var activityList: ArrayList<Activity>

    init {
        this.activityList = mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemActivityListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemBinding.activity = activityList[position]
        holder.itemView.setOnClickListener {
            listener.onClick(activityList[position], it, position)
        }
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    fun updateList(updatedList: ArrayList<Activity>) {
        activityList = updatedList
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Activity {
        return activityList[position]
    }

    inner class CustomViewHolder(
        val itemBinding: ItemActivityListBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

}