package com.krrishshx.musicwiki.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.krrishshx.musicwiki.modelz.TopAlbums_a

class DiffUtil_albumList (private val oldList:List<TopAlbums_a>,private val newList: List<TopAlbums_a>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).img == newList.get(newItemPosition).img
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).title.equals(newList.get(newItemPosition).title)
    }
}