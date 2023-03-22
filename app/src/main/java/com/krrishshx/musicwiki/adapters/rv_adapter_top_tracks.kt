package com.krrishshx.musicwiki.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krrishshx.musicwiki.R
import com.krrishshx.musicwiki.diffUtil.DiffUtil_tracksList
import com.krrishshx.musicwiki.modelb.TopTrack_a
import com.squareup.picasso.Picasso

class rv_adapter_top_tracks  (private val mcontext: Context, private  val listener : OnItemClickListenery) : RecyclerView.Adapter<rv_adapter_top_tracks.ViewHolder>() {
    private var dataSet= ArrayList<TopTrack_a>()

    inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener {
        val tv_item_name : TextView
        val tv_item_title : TextView
        val iv_item_img : ImageView

        init {
            // Define click listener for the ViewHolder's View
            tv_item_name = view.findViewById(R.id.tv_top_track_namex)
            tv_item_title = view.findViewById(R.id.tv_top_track_Titlex)
            iv_item_img = view.findViewById(R.id.iv_top_track_item)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position :Int = adapterPosition
            Log.d("debug:","$position")
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClicky(position,v)
            }

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(mcontext)
            .inflate(R.layout.track_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element


        viewHolder.tv_item_title.text = dataSet.get(position).title
        viewHolder.tv_item_name.text = dataSet.get(position).name

        Picasso.get().load(dataSet.get(position).img).placeholder(R.drawable.music).into(viewHolder.iv_item_img)


    }




    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnItemClickListenery{
        fun onItemClicky(position: Int , v: View?)
    }




    fun setData(newGenreList:List<TopTrack_a>) {
        Log.d("debug::","set data called")

        val diffUtil = DiffUtil_tracksList(dataSet, newGenreList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)

        dataSet = newGenreList as ArrayList<TopTrack_a>
        diffResults.dispatchUpdatesTo(this)
    }


}