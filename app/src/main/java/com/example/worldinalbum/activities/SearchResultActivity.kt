package com.example.worldinalbum.activities

import android.content.Intent
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
    private lateinit var searchResultAdapter: SearchResultAdapter

    private val viewModel: SearchPhotoViewModel by viewModels()

    private val roomViewModel : RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩 초기화
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

        // RecyclerView 초기화
        val searchResultRV = binding.searchResultRecyclerview

        // {position -> ~ / 클릭 이벤트 처리 코드
        searchResultAdapter = SearchResultAdapter(mutableListOf(),
            { item ->

                val intent = Intent(this, SearchResultDetailActivity::class.java)
                intent.putExtra("thumbnail", item.thumbnailUrl)
                startActivity(intent)


            }) { item ->

            val myEntity = MyEntity(thumbnailUrl = item.thumbnailUrl, selected = !item.selected)

            if (item.selected) {
                roomViewModel.saveImagesVM(myEntity)
            } else {
//                myEntity.id = item.id // 기존 엔티티의 id 값을 사용한다.
                roomViewModel.deleteImageVM(myEntity)
            }
            Log.d("myEntity", myEntity.toString())
        }

        searchResultRV.adapter = searchResultAdapter
        searchResultRV.layoutManager = GridLayoutManager(this, 2)

        viewModel.photoLiveData.observe(this, Observer { entityList ->
            val myEntityList = entityList.map { myEntity ->
                MyEntity(
                    thumbnailUrl = myEntity.thumbnailUrl
                )
            }
            // 데이터를 어댑터에 전달합니다.
            searchResultAdapter.setData(myEntityList)


            // 올바른 검색어를 입력하였는지 확인
            if (myEntityList.isNullOrEmpty()) {
                Toast.makeText(this, "검색할 데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}