package com.example.worldinalbum.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.model.ViewPagerData

// 뷰페이저 구성용 어댑터
class ViewPagerAdapter : ListAdapter<ViewPagerData, ViewPagerAdapter.ViewPagerViewHolder>(diffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(getItem(position))


    }

    inner class ViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            private val viewpagerThumb = itemView.findViewById<ImageView>(R.id.viewpager_image)
            private val viewpagerText = itemView.findViewById<TextView>(R.id.viewpager_text)

        fun bind(viewPagerData: ViewPagerData) {
            Log.d("thumb", viewPagerData.thumb)

            Glide.with(MyApp.instance)
                .load(viewPagerData.thumb)
                .into(viewpagerThumb)

        }
    }

    class diffCallBack : DiffUtil.ItemCallback<ViewPagerData>() {
        override fun areItemsTheSame(oldItem: ViewPagerData, newItem: ViewPagerData): Boolean {
            return oldItem.thumb == newItem.thumb
        }

        override fun areContentsTheSame(oldItem: ViewPagerData, newItem: ViewPagerData): Boolean {
            return oldItem == newItem
        }

    }
}