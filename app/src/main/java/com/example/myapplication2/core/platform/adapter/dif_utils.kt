package com.example.myapplication2.core.platform.adapter

import androidx.recyclerview.widget.DiffUtil

interface Diffable {
    fun isSame(other: Any): Boolean
    fun isContentSame(other: Any): Boolean
}

class DiffableListCallback<T : Diffable>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].isSame(newList[newItemPosition])

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].isContentSame(newList[newItemPosition])
}