package com.example.worldinalbum.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldinalbum.model.RecommendSearchData
import kotlinx.coroutines.launch

class SearchPhotoViewModel : ViewModel() {

    private val repository = Repository()

    private val searchDataList = ArrayList<RecommendSearchData>()

    private var _photoLiveData = MutableLiveData<List<RecommendSearchData>>()
    val photoLiveData : LiveData<List<RecommendSearchData>> get() = _photoLiveData

    fun viewModelGetPhoto(searchTerm : String) = viewModelScope.launch {
        val data = repository.repositoryGetPhoto(searchTerm)

        // 데이터 가공을 위한 for 문
        for(dataItem in data.results) {
            Log.d("searchData", dataItem.toString()) // Result(createdAt=2018-02-05T16:58:13Z, id=Mv9hjnEUHR4, likes=2908, tags=[Tag(source=Source

            // 업로드 날짜
            val createDate = dataItem.createdAt
            // 좋아요 수
            val likesCount = dataItem.likes
            // 유저이름
            val userName = dataItem.user.username

            // 원하는 데이터 형식(RecommendSearchData)으로 데이터를 넣고,
            val dataIWant = RecommendSearchData(userName,createDate,likesCount)

            // 데이터리스트
            searchDataList.add(dataIWant)

        }

        Log.d("searchDataList", searchDataList.toString())

        _photoLiveData.value = searchDataList

    }
}