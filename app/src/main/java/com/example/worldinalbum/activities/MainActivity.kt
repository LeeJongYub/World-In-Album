package com.example.worldinalbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.worldinalbum.R
import com.example.worldinalbum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this@MainActivity

        val bottomNavigationView = binding.mainBottomNavigation
        val navController = findNavController(R.id.main_fragment_container)

        bottomNavigationView.setupWithNavController(navController)

        binding.mainTopSearchButton.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}