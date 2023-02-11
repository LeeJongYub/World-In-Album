package com.example.worldinalbum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldinalbum.adapter.SearchResultAdapter
import com.example.worldinalbum.databinding.ActivitySearchResultBinding
import com.example.worldinalbum.model.RecommendSearchData
import com.example.worldinalbum.retrofit.SearchPhotoViewModel

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchResultBinding

    private val viewModel : SearchPhotoViewModel by viewModels()

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchResultBackButton.setOnClickListener {
            finish()
        }

        // 검색어 받아옴
        val getSearchEdit = intent.getStringExtra("search_edit").toString()

        Log.d("searchResult", getSearchEdit) // 받아온 검색어 확인용 로그

        // api 콜
        viewModel.viewModelGetPhoto(getSearchEdit)

        val searchResultRV = binding.searchResultRecyclerview

        viewModel.photoLiveData.observe(this, Observer {
            searchResultAdapter = SearchResultAdapter(it)
            searchResultRV.adapter = searchResultAdapter
            searchResultRV.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        })

    }
}