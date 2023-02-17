package com.example.worldinalbum.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.fragment.MainSearchFragment
import com.example.worldinalbum.model.ViewPagerData

// 뷰페이저 구성용 어댑터
class ViewPagerAdapter(var viewpagerImageList: ArrayList<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))


    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.viewpagerImage.setImageResource(viewpagerImageList[position])
    }

    override fun getItemCount(): Int = viewpagerImageList.size

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)) {
        val viewpagerImage = itemView.findViewById<ImageView>(R.id.viewpager_image)
    }
}