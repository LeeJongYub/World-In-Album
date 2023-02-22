package com.example.worldinalbum.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldinalbum.R
import com.example.worldinalbum.activities.SearchActivity

class SearchAdapter(val searchList : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return searchList.size
    }

    override fun getItem(position: Int): Any {
        return searchList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertview = convertView

        if (convertview == null) {
            convertview = LayoutInflater.from(parent?.context).inflate(R.layout.search_activity_recyclerview_item, parent, false)
        }

        val searchTermText =convertview?.findViewById<TextView>(R.id.search_term_item)
        searchTermText?.text = searchList[position]

        val searchCancelImage =convertview?.findViewById<ImageView>(R.id.search_cancel_item)
        searchCancelImage?.setImageResource(R.drawable.cancel_image)

        return convertview!!
    }

}