package com.krrishshx.musicwiki.frag_genre

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.musicwiki.Album_activity
import com.krrishshx.musicwiki.R
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.adapters.rv_adapter_top_albums
import com.krrishshx.musicwiki.databinding.FragmentTopAlbumsBinding
import com.krrishshx.musicwiki.databinding.FragmentTopTrackBinding
import com.krrishshx.musicwiki.modelz.TopAlbums_a
import com.krrishshx.musicwiki.modelz.albuminfo.Album


class TopAlbumsFragment(var mContext:Context,var vm:Genre_one_ViewModel) : Fragment(), rv_adapter_top_albums.OnItemClickListenerx  {

    lateinit var binding : FragmentTopAlbumsBinding
    lateinit var adapter_album :rv_adapter_top_albums
    public   lateinit var list_my_top_album:ArrayList<TopAlbums_a>
    lateinit var testing :ArrayList<String>
    lateinit var testing1:ArrayList<String>

    var tagx:String =""

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentTopAlbumsBinding.inflate(inflater,container,false)


      binding.rvTopAlbum.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)

          adapter_album =rv_adapter_top_albums(mContext,this)
          binding.rvTopAlbum.adapter = adapter_album


        list_my_top_album = ArrayList<TopAlbums_a>()
        testing = ArrayList<String>()
        testing1 = ArrayList<String>()
       // list_my_top_album.add(TopAlbums_a("hjasf","Sun sathiya","arijit singh"))

      //  adapter_album.setData(list_my_top_album)


        vm.TAG.observe(viewLifecycleOwner, Observer {
            tagx = it.toString()
            Log.d("debug::","21 tag top album --> = ${tagx}")

            if(vm.flag1==1){
                vm.getTopAlbumsList(tagx)
            }

        })


        vm.genre_top_albumsList.observe(viewLifecycleOwner, Observer {
            for(item in it.albums.album){
                Log.d("debug:","21 tag --> ${item.artist.name}")

                list_my_top_album.add(TopAlbums_a("not working",item.name,item.artist.name ))

               // testing.add(item.artist.name)
               // testing1.add(item.name)
               // list_my_top_album.add(TopAlbums_a("xx","xx",item.artist.name))
            }
            Log.d("debug:","21 size == ${list_my_top_album.size}")
             adapter_album.setData(list_my_top_album)
        })

     //       binding.lvTopAlbum.adapter = ArrayAdapter<String>(mContext,android.R.layout.simple_expandable_list_item_1,testing)

//            binding.lvTopAlbum.setOnItemClickListener { parent, view, position, id ->
//
//                var intent = Intent(mContext,Album_activity::class.java)
//
//                intent.putExtra("artist",testing.get(position))
//                intent.putExtra("album",testing1.get(position))
//
//                startActivity(intent)
//
//            }



        return  binding.root
    }

    override fun onItemClickx(position: Int, v: View?) {


        var intent = Intent(mContext,Album_activity::class.java)
        intent.putExtra("artist",list_my_top_album.get(position).artist)
        intent.putExtra("album",list_my_top_album.get(position).title)

        startActivity(intent)




    }

}