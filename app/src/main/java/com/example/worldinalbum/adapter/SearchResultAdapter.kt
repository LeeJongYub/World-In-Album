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
import com.example.worldinalbum.model.RecommendSearchData

class SearchResultAdapter(var dataList: List<RecommendSearchData>) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    // '좋아요' 클릭한 사진을 담아둘 리스트
    val selectImageList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_recyclerview_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        Glide.with(MyApp.instance)
            .load(dataList[position].thumbnail)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.searchImage)


        val likeButtonImage = holder.likeButton
        val selectedItemsImageUrl = dataList[position].thumbnail

        // 리사이클러뷰의 뷰 재활용 특성을 고려하여, 클릭과 무관하게 이미지 처리를 위해 구현
        if (selectImageList.contains(selectedItemsImageUrl)) {
            // 이미 리스트에 포함되어 있다면, 하트로 사진 표시
            likeButtonImage.setImageResource(R.drawable.like_image)
        } else { // 포함 X, 빈 하트로 사진 표시
            likeButtonImage.setImageResource(R.drawable.unlike_image)
        }

        // '좋아요' 버튼을 누를 시
        likeButtonImage.setOnClickListener {

            // 리스트에 사진 url 을 갖고 있다면,
            if (selectImageList.contains(selectedItemsImageUrl)) {
                // 리스트 목록에서 제거하고, 빈 하트로 사진 변경
                selectImageList.remove(selectedItemsImageUrl)
                likeButtonImage.setImageResource(R.drawable.unlike_image)
            } else {
                selectImageList.add(selectedItemsImageUrl)
                likeButtonImage.setImageResource(R.drawable.like_image)
            }

        Log.d("selectImageList", selectImageList.toString())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchImage = itemView.findViewById<ImageView>(R.id.search_result_image_item)
        val likeButton = itemView.findViewById<ImageView>(R.id.search_result_likes_item)
    }
}