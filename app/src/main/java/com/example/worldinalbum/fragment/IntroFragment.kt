package com.example.worldinalbum.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.MainActivity
import com.example.worldinalbum.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goMainButton.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }

}