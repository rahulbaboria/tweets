package com.example.myapplication2.core.platform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class AppListAdapter :
    RecyclerView.Adapter<AppListAdapter.AppListViewHolder>() {

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun itemClicked(position: Int)
    abstract fun viewHolder(view: View): AppListViewHolder
    abstract fun onCustomBindViewHolder(holder: AppListViewHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListViewHolder =
        viewHolder(
            LayoutInflater.from(parent.context).inflate(
                layoutId(),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AppListViewHolder, position: Int) {
        holder.bind()
        onCustomBindViewHolder(holder, position)
    }

    abstract inner class AppListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind()
    }


    inner class ItemClickViewHolder(view: View) : AppListViewHolder(view) {
        override fun bind() {
            itemView.setOnClickListener {
                itemClicked(adapterPosition)
            }
        }
    }
}