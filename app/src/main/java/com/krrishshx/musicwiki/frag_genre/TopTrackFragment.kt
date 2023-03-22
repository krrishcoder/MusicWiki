package com.krrishshx.musicwiki.frag_genre

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.musicwiki.R
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.adapters.rv_adapter_top_albums
import com.krrishshx.musicwiki.adapters.rv_adapter_top_artist
import com.krrishshx.musicwiki.adapters.rv_adapter_top_tracks
import com.krrishshx.musicwiki.databinding.FragmentTopTrackBinding
import com.krrishshx.musicwiki.modela.TopArtist_a
import com.krrishshx.musicwiki.modelb.TopTrack_a


class TopTrackFragment(var mContext: Context,var vm: Genre_one_ViewModel) : Fragment() , rv_adapter_top_tracks.OnItemClickListenery{

    lateinit var binding: FragmentTopTrackBinding
    lateinit var adapter_tracks : rv_adapter_top_tracks
    public   lateinit var list_my_top_tracks:ArrayList<TopTrack_a>

    lateinit var testing :ArrayList<String>
var tagx =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopTrackBinding.inflate(inflater,container,false)



       binding.rvTopTracks.layoutManager = LinearLayoutManager(mContext,
           LinearLayoutManager.VERTICAL,false)

      adapter_tracks= rv_adapter_top_tracks(mContext,this)
      binding.rvTopTracks.adapter =adapter_tracks




     //   testing = ArrayList<String>()
        list_my_top_tracks = ArrayList<TopTrack_a>()




        vm.TAG.observe(viewLifecycleOwner, Observer {
            tagx = it.toString()
            Log.d("debug::","21 track 1st --> = ${tagx}")
            if(vm.flag1==1){
                vm.getTopTracksList(tagx)
            }

        })



        vm.genre_top_tracksList.observe(viewLifecycleOwner, Observer {


            for(item in it.tracks.track){
                Log.d("debug::","21 tag top track 2nd  --> = ${item.image.get(2).text} ${item.name} and ${item.artist.name}")
                list_my_top_tracks.add(TopTrack_a(item.image.get(1).text,item.name, item.artist.name))
            }
            Log.d("debug::","21 tag track 3rd size  --> = ${list_my_top_tracks.size}")

            adapter_tracks.setData(list_my_top_tracks)
        })








        return binding.root
    }

    override fun onItemClicky(position: Int, v: View?) {

    }

}