package com.example.worldinalbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.worldinalbum.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToMainButton.setOnClickListener {
            finish()
        }

        // 검색어 뷰 호출
        val searchEdit = binding.searchEditText


        // 검색버튼 누를 시, 검색한 텍스트 전송
        binding.searchButton.setOnClickListener {

            val intent = Intent(this, SearchResultActivity::class.java)

            // 검색어 전달
            intent.putExtra("search_edit", searchEdit.text.toString())

            startActivity(intent)
        }


    }
}