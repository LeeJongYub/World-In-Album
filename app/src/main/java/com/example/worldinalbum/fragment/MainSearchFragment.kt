package com.example.worldinalbum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.worldinalbum.R
import com.example.worldinalbum.databinding.FragmentMainSearchBinding
import com.example.worldinalbum.retrofit.SearchPhotoViewModel


class MainSearchFragment : Fragment() {

    private var _binding : FragmentMainSearchBinding? = null
    val binding get() = _binding!!

//    private val viewModel : SearchPhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.viewModelGetPhoto()

    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}