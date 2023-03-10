package com.example.worldinalbum.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldinalbum.room.MyEntity
import kotlinx.coroutines.launch

class SearchPhotoViewModel : ViewModel() {

    private val repository = Repository()

    private val searchDataList = ArrayList<MyEntity>()

    private var _photoLiveData = MutableLiveData<List<MyEntity>>()

    val photoLiveData: LiveData<List<MyEntity>> get() = _photoLiveData

    fun viewModelGetPhoto(searchTerm: String) = viewModelScope.launch {
        val data = repository.repositoryGetPhoto(searchTerm)
        Log.d("searchData", data.toString())

        // 데이터 가공을 위한 for 문
        for (dataItem in data.results) {
            Log.d(
                "searchData_for",
                dataItem.toString()
            ) // Result(createdAt=2018-02-05T16:58:13Z, id=Mv9hjnEUHR4, likes=2908, tags=[Tag(source=Source

            // 업로드 날짜
            val createDate = dataItem.createdAt
            // 좋아요 수
            val likesCount = dataItem.likes
            // 유저이름
            val userName = dataItem.user.username
            // 사진
            val thumbImage = dataItem.urls.thumb

            // 하트 표시여부 체크용 boolean / false 로 초기화
            var selected : Boolean = false

            // 원하는 데이터 형식(RecommendSearchData)으로 데이터를 넣고,
            val dataIWant = MyEntity(thumbnailUrl = thumbImage)

            // 데이터리스트에 데이터형식을 넣어줌
            searchDataList.add(dataIWant)

        }

        Log.d("searchDataList", searchDataList.toString())

        _photoLiveData.value = searchDataList

    }

}