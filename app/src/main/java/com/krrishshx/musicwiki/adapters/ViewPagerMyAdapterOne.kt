package com.krrishshx.musicwiki.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.frag_genre.TopAlbumsFragment
import com.krrishshx.musicwiki.frag_genre.TopArtistFragment
import com.krrishshx.musicwiki.frag_genre.TopTrackFragment

class ViewPagerMyAdapterOne(fa: FragmentActivity, val mContext:Context,val vm:Genre_one_ViewModel) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int=3

    override fun createFragment(position: Int): Fragment {

        if(position==0){
            return TopAlbumsFragment(mContext,vm)
        }else if(position==1){
            return TopArtistFragment(mContext,vm)
        }else{
            return TopTrackFragment(mContext,vm)
        }
    }
}