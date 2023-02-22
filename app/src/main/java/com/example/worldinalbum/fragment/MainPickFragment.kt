package com.example.worldinalbum.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.MainPickFragDetailActivity
import com.example.worldinalbum.activities.SearchResultActivity
import com.example.worldinalbum.activities.SearchResultDetailActivity
import com.example.worldinalbum.adapter.MainPickAdapter
import com.example.worldinalbum.adapter.SearchResultAdapter
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.databinding.FragmentMainPickBinding
import com.example.worldinalbum.model.RecommendSearchData
import com.example.worldinalbum.room.MyEntity
import com.example.worldinalbum.room.RoomViewModel
import com.example.worldinalbum.viewmodel.DataStoreViewModel


class MainPickFragment : Fragment() {

    private var _binding: FragmentMainPickBinding? = null
    val binding get() = _binding!!

    val getUrls = ArrayList<String>()

    lateinit var mainPickAdapter: MainPickAdapter

    val urlsList = ArrayList<String>()

    private val viewModel: RoomViewModel by activityViewModels()

    // ----------------------------
    // 기존 위의 id 값을 datastore 의 아이디 값으로 변경 예정
    private lateinit var dataStoreViewModel: DataStoreViewModel
    // ----------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainPickBinding.inflate(inflater, container, false)

        return binding.root
    }

    fun getImageUrl(url: ArrayList<String>) {

        getUrls.add(url.toString())
        Log.d("getUrls", getUrls.toString())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImagesVM()
        Log.d("getImagesVM", viewModel.getImagesVM().toString())

        dataStoreViewModel = DataStoreViewModel()
    }

    override fun onResume() {
        super.onResume()

        // room - 조회시 중복 삭제
        // --------------------
        urlsList.clear()
        // --------------------

        val rv = binding.mainPickFragRecyclerview

        viewModel.roomSaveImageLiveData.observe(viewLifecycleOwner, Observer {
            // it : [MyEntity(id=1, thumbnailUrl=https://images.unsplash.com/photo-

            for (item in it) {

                val url = item.thumbnailUrl
                // url : https://images.unsplash.com/photo1, https://images.unsplash.com/photo2
                urlsList.add(url)

            }

            mainPickAdapter = MainPickAdapter(urlsList)
            mainPickAdapter.notifyDataSetChanged()

            mainPickAdapter.setMyItemClickListener(object : MainPickAdapter.MyItemClickListener {
                override fun onImageItemClick(position: Int) {
                    val intent = Intent(requireContext(), MainPickFragDetailActivity::class.java)
                    intent.putExtra("url", urlsList[position])
                    startActivity(intent)
                }

                override fun onLikesItemClick(position: Int) {

                    val id = urlsList.indexOf(urlsList[position]).inc()
                    // 클릭한 아이템의 인덱스 0, 1, 2 ~ , 데이터베이스의 id 값에 맞춰주기 위해 inc()를 붙임

                    var thumbnailUrl = urlsList[position]
                    Log.d("url2_thumbnailUrl", urlsList[position])
                    viewModel.deleteImageVM(MyEntity(id, thumbnailUrl, false))

                    // indexOutOfBound 방지 처리
                    if (urlsList.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "찜한 사진이 없습니다.", Toast.LENGTH_SHORT).show()
                        Log.d("url2_nullOrEmpty", urlsList[position])
                    } else {
                        urlsList.remove(thumbnailUrl)
                        mainPickAdapter.notifyDataSetChanged()
                    }

                    Log.d("url2_position", id.toString())

                    // 이슈 트래블 슈팅 - arrlist 의 배열
                    // MyEntity(urlsList.indexOf(urlsList[position]).toString(), urlsList[position], false)
                }

            })
            rv.adapter = mainPickAdapter
            rv.layoutManager = LinearLayoutManager(MyApp.instance)
//            mainPickAdapter.notifyDataSetChanged()
        })


        // 모두지우기 텍스트뷰 클릭시 모두 지우기
        binding.mainPickFragDeleteAll.setOnClickListener {
            viewModel.deleteAllImageVM()

            // 리사이클러뷰 업데이트(추가, 삭제 등)시 clear 후, 어댑터에 상황알리기 기능 추가
            urlsList.clear()
            mainPickAdapter.notifyDataSetChanged()

            // 이슈 트러블 슈팅 - datastore resetId
            // -----------------
            dataStoreViewModel.resetIdVM()
            Log.d("countIdd_reset", dataStoreViewModel.resetIdVM().toString())
            // ------------------
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}