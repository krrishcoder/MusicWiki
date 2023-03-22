package com.krrishshx.musicwiki.diffUtil

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil(private val oldList:List<String>,private val newList: List<String>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
             return oldList.get(oldItemPosition) == newList.get(newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition))
    }
}