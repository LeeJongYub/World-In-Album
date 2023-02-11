package com.example.worldinalbum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.worldinalbum.R

class TodayGoodWordAdapter(val goodWordList : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return goodWordList.size
    }

    override fun getItem(position: Int): Any {
        return goodWordList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertview = convertView

        if (convertview == null) {
            convertview = LayoutInflater.from(parent?.context).inflate(R.layout.good_word_listview_item, parent, false)
        }

        val item =convertview?.findViewById<TextView>(R.id.good_word_text_item)
        item?.text = goodWordList[position]

        return convertview!!
    }


}