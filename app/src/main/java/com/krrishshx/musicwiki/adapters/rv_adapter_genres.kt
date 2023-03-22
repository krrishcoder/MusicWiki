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
import com.krrishshx.musicwiki.diffUtil.MyDiffUtil

class rv_adapter_genres  (private val mcontext: Context, private  val listener : OnItemClickListener) : RecyclerView.Adapter<rv_adapter_genres.ViewHolder>() {
    private var dataSet= ArrayList<String>()

    inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener {
        val tv_item : TextView

        init {
            // Define click listener for the ViewHolder's View
            tv_item = view.findViewById(R.id.tv_genre_text)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position :Int = adapterPosition
            Log.d("debug:","$position")
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position,v)
            }

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(mcontext)
            .inflate(R.layout.genre_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element


        viewHolder.tv_item.text = dataSet.get(position)





    }




    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnItemClickListener{
        fun onItemClick(position: Int , v: View?)
    }




    fun setData(newGenreList:List<String>) {
        val diffUtil = MyDiffUtil(dataSet, newGenreList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)

        dataSet = newGenreList as ArrayList<String>
        diffResults.dispatchUpdatesTo(this)
    }


}