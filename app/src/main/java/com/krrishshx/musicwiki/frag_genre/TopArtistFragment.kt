package com.krrishshx.musicwiki.frag_genre

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.musicwiki.Album_activity
import com.krrishshx.musicwiki.Artist_activity
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.adapters.rv_adapter_top_albums
import com.krrishshx.musicwiki.adapters.rv_adapter_top_artist
import com.krrishshx.musicwiki.databinding.FragmentTopArtistBinding
import com.krrishshx.musicwiki.modela.TopArtist_a


class TopArtistFragment(var mContext: Context,val vm:Genre_one_ViewModel) : Fragment(),rv_adapter_top_artist.OnItemClickListener{

    lateinit var adapter_artist : rv_adapter_top_artist
    public   lateinit var list_my_top_artist:ArrayList<TopArtist_a>
    lateinit var testing:ArrayList<String>

    var tagx =""

    lateinit var binding: FragmentTopArtistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopArtistBinding.inflate(inflater,container,false)


        binding.rvTopArtist.layoutManager = LinearLayoutManager(mContext,
            LinearLayoutManager.VERTICAL,false)

        adapter_artist = rv_adapter_top_artist(mContext,this)
        binding.rvTopArtist.adapter = adapter_artist



list_my_top_artist = ArrayList<TopArtist_a>()
                 //testing = ArrayList<String>()

        vm.TAG.observe(viewLifecycleOwner, Observer {
            tagx = it.toString()
            Log.d("debug::","21 tag top artist --> = ${tagx}")

            if(vm.flag1==1){
                vm.getTopArtistList(tagx)
            }

        })


        vm.genre_top_artistList.observe(viewLifecycleOwner, Observer {

            for(item in it.topartists.artist){
                Log.d("debug::","21 tag top artist 2nd  --> = ${item.name}")
                list_my_top_artist.add(TopArtist_a("not working",item.name))
            }
            Log.d("debug::","21 tag top artist 2nd size  --> = ${list_my_top_artist.size}")
            adapter_artist.setData(list_my_top_artist)
        })




//        vm.genre_top_artistList.observe(viewLifecycleOwner, Observer {
//            for(item in it.topartists.artist ){
//                Log.d("debug:","21 tag --> ${item.name}")
//                testing.add(item.name)
//                list_my_top_artist.add(TopArtist_a("not working",item.name))
//
//                // list_my_top_album.add(TopAlbums_a("xx","xx",item.artist.name))
//            }
//            //     adapter_artist.setData(list_my_top_artist)
//
//       //     binding.lvTopArtist.adapter = ArrayAdapter<String>(mContext,
//        //        R.layout.simple_expandable_list_item_1,testing)
//
//
//
//
//
//        })


//        binding.lvTopArtist.setOnItemClickListener { parent, view, position, id ->
//
//            var intent = Intent(mContext, Artist_activity::class.java)
//            intent.putExtra("artist",testing.get(position))
//            startActivity(intent)
//
//        }


        return binding.root
    }

    override fun onItemClick(position: Int, v: View?) {
          var intent = Intent(mContext, Artist_activity::class.java)
           intent.putExtra("artist",list_my_top_artist.get(position).name)
           startActivity(intent)

    }


}