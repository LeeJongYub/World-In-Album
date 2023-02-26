package com.example.worldinalbum.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.MainPickFragDetailActivity
import com.example.worldinalbum.adapter.MainPickAdapter
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.databinding.FragmentMainPickBinding
import com.example.worldinalbum.room.MyEntity
import com.example.worldinalbum.room.RoomViewModel


class MainPickFragment : Fragment() {

    private lateinit var binding : FragmentMainPickBinding

    val getUrls = ArrayList<String>()

    lateinit var mainPickAdapter: MainPickAdapter

    val urlsList = ArrayList<String>()

    private val viewModel: RoomViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_pick, container, false)

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

                    var thumbnailUrl = urlsList[position]
                    Log.d("url2_thumbnailUrl", urlsList[position])
                    viewModel.deleteImageVM(MyEntity(thumbnailUrl = thumbnailUrl))

                    // indexOutOfBound 방지 처리
                    if (urlsList.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "찜한 사진이 없습니다.", Toast.LENGTH_SHORT).show()
                        Log.d("url2_nullOrEmpty", urlsList[position])
                    } else {
                        urlsList.remove(thumbnailUrl)
                        mainPickAdapter.notifyDataSetChanged()
                    }

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
        }
    }

}