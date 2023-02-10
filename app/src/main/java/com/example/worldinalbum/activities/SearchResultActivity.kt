package com.example.worldinalbum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.worldinalbum.databinding.ActivitySearchResultBinding
import com.example.worldinalbum.retrofit.SearchPhotoViewModel

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchResultBinding

    private val viewModel : SearchPhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 검색어 받아옴
        val getSearchEdit = intent.getStringExtra("search_edit").toString()

        Log.d("searchResult", getSearchEdit) // 받아온 검색어 확인용 로그

        // api 콜
        viewModel.viewModelGetPhoto(getSearchEdit)

    }
}