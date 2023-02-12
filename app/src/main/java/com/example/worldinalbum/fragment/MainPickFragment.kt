package com.example.worldinalbum.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.SearchResultActivity
import com.example.worldinalbum.adapter.MainPickAdapter
import com.example.worldinalbum.adapter.SearchResultAdapter
import com.example.worldinalbum.constants.MyApp
import com.example.worldinalbum.databinding.FragmentMainPickBinding
import com.example.worldinalbum.model.RecommendSearchData


class MainPickFragment : Fragment(){

    private var _binding : FragmentMainPickBinding? = null
    val binding get() = _binding!!

    private lateinit var mainPickAdapter: MainPickAdapter

    val getUrls = ArrayList<String>()

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

    fun getImageUrl(url : ArrayList<String>) {

        getUrls.add(url.toString())
        Log.d("getUrls", getUrls.toString())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPickAdapter = MainPickAdapter(getUrls)

        val mainPickRv = binding.mainPickFragRecyclerview

        mainPickRv.adapter = mainPickAdapter
        mainPickRv.layoutManager = LinearLayoutManager(MyApp.instance, LinearLayoutManager.VERTICAL, false)

        Log.d("getUrlsList", getUrls.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}