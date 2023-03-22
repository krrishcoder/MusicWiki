package com.krrishshx.musicwiki.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.krrishshx.musicwiki.modela.TopArtist_a
import com.krrishshx.musicwiki.modelz.TopAlbums_a

class DiffUtil_artistList  (private val oldList:List<TopArtist_a>, private val newList: List<TopArtist_a>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).name == newList.get(newItemPosition).name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).name.equals(newList.get(newItemPosition).name)
    }
}