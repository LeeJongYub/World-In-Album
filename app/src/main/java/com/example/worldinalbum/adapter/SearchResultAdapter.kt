package com.example.worldinalbum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.databinding.SearchResultRecyclerviewItemBinding
import com.example.worldinalbum.room.MyEntity

class SearchResultAdapter(
    var dataList: MutableList<MyEntity>,
    private val onClickListener: (MyEntity) -> Unit,
    private val onItemLikedListener: (MyEntity) -> Unit,
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    // 선택된 아이템의 위치를 저장할 변수
    private var selectedItemPosition = -1

    init {
        for (i in 0 until dataList.size) {
            dataList[i].selected = false
        }
    }

    fun setData(myEntityList: List<MyEntity>) {
        this.dataList = myEntityList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultRecyclerviewItemBinding.inflate(inflater, parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

        // 선택된 아이템의 isSelected 속성을 true 로 설정하고,
        // 인접한 아이템들의 isSelected 속성을 false 로 설정
        holder.itemView.setOnClickListener {

            if (selectedItemPosition != holder.adapterPosition) {
                if (selectedItemPosition != -1) {
                    dataList[selectedItemPosition].selected = false
                    notifyItemChanged(selectedItemPosition)
                }
                selectedItemPosition = holder.adapterPosition
                dataList[selectedItemPosition].selected = true
                notifyItemChanged(selectedItemPosition)
            }
        }

        if (item.selected) {
            holder.searchlikeButton.setImageResource(R.drawable.like_image)
        } else {
            holder.searchlikeButton.setImageResource(R.drawable.unlike_image)
        }

        holder.searchlikeButton.setOnClickListener {
            item.selected = !item.selected // 클릭할 때마다 선택 여부 반전
            onItemLikedListener(item)

            if (item.selected) {
                holder.searchlikeButton.setImageResource(R.drawable.like_image)
            } else {
                holder.searchlikeButton.setImageResource(R.drawable.unlike_image)
            }
        }
        holder.itemView.setOnClickListener {
            onClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class SearchResultViewHolder(private val binding: SearchResultRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val searchImage = itemView.findViewById<ImageView>(R.id.search_result_image_item)
        val searchlikeButton = itemView.findViewById<ImageView>(R.id.search_result_likes_item)

        fun bind(item: MyEntity) {
            binding.data = item
            Glide.with(itemView.context)
                .load(item.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(searchImage)
        }
    }
}