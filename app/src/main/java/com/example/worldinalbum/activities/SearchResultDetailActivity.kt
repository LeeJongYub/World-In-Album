package com.example.worldinalbum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.worldinalbum.R

class SearchResultDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result_detail)

        val imageData = intent.getSerializableExtra("thumbData")
        val selectBooleanData = intent.getBooleanExtra("likeData",false)

        val searchResultDetailImage = findViewById<ImageView>(R.id.search_result_detail_image)
        val selectBooleanImage = findViewById<ImageView>(R.id.search_result_detail_likes_image)

        Log.d("imageData", imageData.toString())
        Log.d("selectBooleanData", selectBooleanData.toString())

        Glide.with(this)
            .load(imageData.toString())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(searchResultDetailImage)

        if (selectBooleanData == true) {
            selectBooleanImage.setImageResource(R.drawable.like_image)
        } else {
            selectBooleanImage.setImageResource(R.drawable.unlike_image)
        }


    }
}