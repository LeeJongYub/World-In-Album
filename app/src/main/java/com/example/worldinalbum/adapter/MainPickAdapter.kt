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

    private val getUrlsList = MainPickFragment()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPickViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_recyclerview_item, parent, false)

        return MainPickViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPickViewHolder, position: Int) {

        val urls = getUrlsList.getUrls
        Log.d("urls", urls.toString())

        Glide.with(MyApp.instance)
            .load(getUrlList[position])
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.pickImage)
        Log.d("urlList", getUrlList.toString())
    }

    override fun getItemCount(): Int {
        return getUrlList.size
        Log.d("urlListSize", getUrlList.size.toString())
    }

    inner class MainPickViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val pickImage = itemView.findViewById<ImageView>(R.id.search_result_image_item)
        val pickLikes = itemView.findViewById<ImageView>(R.id.search_result_likes_item)
    }
}