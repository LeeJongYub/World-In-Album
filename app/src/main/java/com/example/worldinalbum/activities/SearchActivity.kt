package com.example.worldinalbum.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.example.worldinalbum.R
import com.example.worldinalbum.adapter.SearchAdapter
import com.example.worldinalbum.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    // 키보드 설정
    private lateinit var inputMethodManager: InputMethodManager

    private val searchList = mutableListOf<String>()

    private lateinit var searchAdapter: SearchAdapter

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

                inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

    override fun onResume() {
        super.onResume()

        binding.searchEditText.requestFocus()

        // 검색어 리스트뷰에 추가
        val searchEditToString = binding.searchEditText.text.toString()

        val searchTermListview = binding.searchTermListview


        if (binding.searchEditText.text.isNullOrEmpty()) {
            searchTermListview.visibility = View.GONE
        } else {
            searchList.add(searchEditToString)
            searchList.distinct()

            searchTermListview.visibility = View.VISIBLE
            searchAdapter = SearchAdapter(searchList)
            searchTermListview.adapter = searchAdapter
        }

        // 리스트뷰 클릭시 삭제
        searchTermListview.setOnItemClickListener { parent, view, position, id ->

            searchList.remove(searchList[position])
            searchAdapter.notifyDataSetChanged()

        }

    }
}