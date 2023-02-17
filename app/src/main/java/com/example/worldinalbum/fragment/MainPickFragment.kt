package com.example.worldinalbum.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.SearchResultActivity
import com.example.worldinalbum.adapter.MainPickAdapter
import com.example.worldinalbum.adapter.SearchResultAdapter
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.databinding.FragmentMainPickBinding
import com.example.worldinalbum.model.RecommendSearchData
import com.example.worldinalbum.room.MyEntity
import com.example.worldinalbum.room.RoomViewModel


class MainPickFragment : Fragment() {

    private var _binding: FragmentMainPickBinding? = null
    val binding get() = _binding!!

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

        val rv = binding.mainPickFragRecyclerview

        viewModel.roomSaveImageLiveData.observe(viewLifecycleOwner, Observer {
            // it : [MyEntity(id=1, thumbnailUrl=https://images.unsplash.com/photo-

            for (item in it) {

                val url = item.thumbnailUrl
                // url : https://images.unsplash.com/photo1, https://images.unsplash.com/photo2
                urlsList.add(url)

            }

                mainPickAdapter = MainPickAdapter(urlsList)
                Log.d("urlList2", urlsList.toString())
                rv.adapter = mainPickAdapter
                rv.layoutManager = LinearLayoutManager(MyApp.instance)

        })

    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}