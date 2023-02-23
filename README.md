# World-In-Album
**World In Album**은 unSplash API를 이용한 사진 조회, 공유, 로컬저장 기능을 가진 앱입니다.

</br></br>

- Stacks (기술 스택)</br>

  <img width="50" src="https://img.shields.io/badge/Kotlin-0095D5?style=flat-square&logo=Kotlin&logoColor=white">
  
  </br>
  
  <img width="65" src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=white">

  </br>

```
- 사용한 라이브러리 -

  Retrofit
  Gson
  Navigation
  Room
  ViewModel
  LiveData
  ViewPager
  Indicator
  Lottie
  Glide
```

</br></br></br>

## - App Preview -
</br></br></br>

<p align="center">
  <img src="https://user-images.githubusercontent.com/113434642/220926456-928be48c-8a3c-4fe6-98b8-bd06f16f16d6.gif">
</p>


</br></br></br>
## - 앱 내부 기능 -
1. 사진 조회
2. 사진 공유
3. 사진 저장(외부저장소)


</br></br></br>

## - 문제 아카이브 -


### - 문제 1 :

</br>

- 문제

  MainPickFragment에서 MyEntity의 id 값과, 
  
  RoomDatabase의 id를 일치시키는 과정에서 막힘 발생

</br>

- 해결

  MainPickAdapter에 넣게될 사진 data인 urlList의 position 값을 찾아야겠다고 생각함

  1) 먼저 MainPickAdapter에서 interface(MyItemClickListener)를 선언

  2) MainPickFragment에 다음과 같은 변수를 선언

　　　(해당 변수가 실제 Database에서 삭제되는 id와 일치되기 때문에 문제없이 삭제됨)

</br>

- 코드
```kotlin
override fun onLikesItemClick(position: Int) {

val id = urlList.indexOf(urlList[position]).inc()

// view 클릭시 필요한 다른 처리
// ~
}
```

</br>

- 코드 의도

  1) 인터페이스를 통해 받은 onLikesItemClick 메소드에서 urlList의 position 값을 찾는 코드

  2) .inc()는 프로젝트의 데이터베이스 id가 1부터 계수되는 것과,</br>
    프래그먼트 urlList의 index가 0부터 계수됨을 고려하여 일치를 위해 추가함</br>

</br></br></br>

### - 문제 2 :

</br>

- 문제

  MainPickFragment에서 '모두지우기' Textview 클릭 시 UI의 즉시 업데이트가 필요하나,</br>
  ui 즉시 변경을 위한 코드를 모르는 상태

</br>

- 해결

  clear(), notifyDataSetChanged()를 통해 ui 업데이트가 가능함을 알게됨

  1) 해당 프래그먼트가 Foreground에 있는 상황에서 수정이 필요하기 때문에, onResume() 콜백 메소드 내에서 코드 구현

  2) clear()를 통해 리사이클러뷰에 표시될 urlList를 비움

  3) notifyDataSetChanged()을 통해 어댑터에 데이터가 변경되었음을 알려줌

</br>

- 코드
```kotlin
override fun onResume() {
    super.onResume()
    
    binding.mainPickFragDeleteAll.setOnClickListener {
    
    urlList.clear() 
    mainPickAdapter.notifyDataSetChanged()
    
    // 모두 지우기 textview 클릭시 필요한 다른 처리
    // ~
    }
```

</br>

- 코드 의도

  1) '모두 지우기' Textview 클릭 -></br>
    RoomDatabase의 전체 데이터 삭제 및 Recyclerview UI 변경
  
  </br></br></br>

### - 문제 3 :

</br>

- 문제

  1. 검색어를 Listview를 통해 SearchActivity에 배치,</br>
  아이템을 클릭 시 자동 삭제를 구현하는 과정에서 Listview의 특성상</br>
  Recyclerview처럼 뷰를 재사용하지 않기 때문에 리스트가 많아질 시 리소스 낭비가 발생</br>
   
</br>

- 해결

  최대로 표시할 수 있는 검색어 개수를 지정해둠으로써 해결함

</br>

- 코드
```kotlin
override fun onResume() {
    super.onResume()
        // 검색어
        val searchEditToString = binding.searchEditText.text.toString()
        // 리스트뷰
        val searchTermListview = binding.searchTermListview

        if (binding.searchEditText.text.isNullOrEmpty()) {
            searchTermListview.visibility = View.GONE
        } else {
            // 검색어가 5개가 넘어가면 처음 검색한 것부터 삭제되고 방금 검색한 것을 추가
            if (searchList.size >= 5) {
                searchList.remove(searchList[0])
                searchList.add(searchEditToString)
                // 검색어 추가
            } else {
                 searchList.add(searchEditToString)
            }

            searchTermListview.visibility = View.VISIBLE
        }
    }
```

</br>

- 코드 의도

  1) Listview에 들어가는 데이터 searchList의 size가 5가 넘어갈 시, 처음 검색어를 삭제

  2) 방금 검색한 검색어를 추가
  
  3) Listview 값 존재 여부에 따라 visibility로 UI 변경
  
</br></br></br>

## - 수정 필요 -

1. 검색어 표시 문제 (SearchResultAcitivty -> SearchActivity 뿐만 아니라 MainActivity -> SearchActivity 때도 검색어 표시 원함)</br>

    (1) Datastore를 통해 검색어들이 Activity의 생명주기와 상관없이 로컬로 저장

</br>

2. '좋아요' 버튼이 여러 Activity, Adapter에 위치함에 따른 RoomDatabase에 데이터 추가, 삭제시 id 값 일치 문제</br>

    (1) Database에 저장, 삭제하는 모든 부분을 Datastore를 통해 id 값이 일치되도록 리팩토링 에정

</br>

3. 최근 검색어가 상단에 오도록 배치</br>

    (1) Listview에 .add() -> .insert(0, item)으로 수정

</br>

4. MainSearchFragment에 표시되는 명언의 글자 수가 많을 경우 짤림 현상 발생</br>

    (1) text size 조절</br>
    (2) 긴 명언 삭제
