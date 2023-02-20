package com.example.worldinalbum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldinalbum.adapter.SearchResultAdapter
import com.example.worldinalbum.databinding.ActivitySearchResultBinding
import com.example.worldinalbum.retrofit.SearchPhotoViewModel
import com.example.worldinalbum.room.MyEntity
import com.example.worldinalbum.room.RoomViewModel

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding

    private val viewModel: SearchPhotoViewModel by viewModels()

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

        // 툴바 타이틀에 검색어 배치
        binding.searchResultToolbarTitle.text = getSearchEdit

        // api
        viewModel.viewModelGetPhoto(getSearchEdit)

        val searchResultRV = binding.searchResultRecyclerview

        viewModel.photoLiveData.observe(this, Observer {

            // 올바른 검색어를 입력하였는지 확인
            if(it.isNullOrEmpty()) {
                Toast.makeText(this, "검색할 데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
            searchResultAdapter = SearchResultAdapter(it)
            searchResultRV.adapter = searchResultAdapter
            searchResultRV.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            }

            // -----

//            for (items in it) {
//                val url = items.thumbnail
//                Log.d("yes", url)
//            }
//            val viewModel = RoomViewModel()
//
//            for (items in it) {
//                val url = items.thumbnail
//                Log.d("url", url.toString())
//
//                if (getSelectBoolean == true) {
//
//                viewModel.saveImagesVM(MyEntity(0,url))
//                Log.d("viewmodel", viewModel.saveImagesVM(MyEntity(0,url)).toString())
//
//                }
//
//            }

            // -----

        })


    }

}