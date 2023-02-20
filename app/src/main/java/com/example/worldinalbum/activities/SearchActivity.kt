package com.example.worldinalbum.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.worldinalbum.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    // 키보드 설정
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBackButton.setOnClickListener {
            finish()
        }

        // 검색어 뷰 호출
        val searchEdit = binding.searchEditText


        // 검색버튼 누를 시, 검색한 텍스트 전송
        binding.searchButton.setOnClickListener {

            if (searchEdit.text.isNullOrEmpty()) {
                searchEdit.requestFocus()

                inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(searchEdit, InputMethodManager.SHOW_IMPLICIT)

                Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, SearchResultActivity::class.java)

                // 검색어 전달
                intent.putExtra("search_edit", searchEdit.text.toString())

                startActivity(intent)

            }
        }


    }
}