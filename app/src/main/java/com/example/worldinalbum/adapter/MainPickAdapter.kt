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
import com.example.worldinalbum.room.MyEntity
import com.example.worldinalbum.room.RoomViewModel

class MainPickAdapter(
    private var getUrlList: ArrayList<String>) :
    RecyclerView.Adapter<MainPickAdapter.MainPickViewHolder>() {

    // 클릭리스너 인터페이스를 정의
    interface MyItemClickListener {
        fun onImageItemClick(position: Int)
        fun onLikesItemClick(position: Int)
    }

    // 클릭 리스너 선언
    private lateinit var mItemClickListener: MyItemClickListener

    // 클릭 리스너 등록 메서드
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    inner class MainPickViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.findViewById<ImageView>(R.id.search_result_image_item).setOnClickListener {
                mItemClickListener.onImageItemClick(adapterPosition)
            }
            itemView.findViewById<ImageView>(R.id.search_result_likes_item).setOnClickListener {
                mItemClickListener.onLikesItemClick(adapterPosition)
            }
        }

        val pickImage = itemView.findViewById<ImageView>(R.id.search_result_image_item)
        val pickLikes = itemView.findViewById<ImageView>(R.id.search_result_likes_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPickViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_pick_frag_recyclerview_item, parent, false)
        return MainPickViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPickViewHolder, position: Int) {

        Glide.with(MyApp.instance)
            .load(getUrlList[position])
            .into(holder.pickImage)

        // 하트 클릭시 해당 이미지 지우기 구현
        // ---------------------------------------------------------------------------
//        var selected = false
//        val viewModel = RoomViewModel()
//
//        holder.pickLikes.setOnClickListener {
//            viewModel.deleteImageVM(MyEntity(0, getUrlList[position], selected))
//        }
        // ---------------------------------------------------------------------------


    }


    override fun getItemCount(): Int {
        return getUrlList.size
    }


}
