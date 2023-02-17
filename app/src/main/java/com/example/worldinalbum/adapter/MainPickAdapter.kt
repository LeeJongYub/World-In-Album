package com.example.worldinalbum.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.fragment.MainPickFragment

class MainPickAdapter(val getUrlList : ArrayList<String>) : RecyclerView.Adapter<MainPickAdapter.MainPickViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPickViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_recyclerview_item, parent, false)
        return MainPickViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPickViewHolder, position: Int) {
        Glide.with(MyApp.instance)
            .load(getUrlList[position])
            .into(holder.pickImage)
    }


    override fun getItemCount(): Int {
        return getUrlList.size
    }

    inner class MainPickViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val pickImage = itemView.findViewById<ImageView>(R.id.search_result_image_item)
        val pickLikes = itemView.findViewById<ImageView>(R.id.search_result_likes_item)
    }
}
