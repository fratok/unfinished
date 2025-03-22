package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.databinding.ActivityItem2Binding


class  ItemFragment : BaseFragment<ActivityItem2Binding>() {


    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActivityItem2Binding = ActivityItem2Binding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemListTitleOne.text = "itemTitle"
        binding.itemListTitleOne.text = "itemText"
        binding.back.setOnClickListener { findNavController().popBackStack() }


    }
}


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item2)
//
//        val title: TextView = findViewById(R.id.item_list_title_one)
//        val text: TextView = findViewById(R.id.item_list_text)
//
//
//        title.text = intent.getStringExtra("itemTitle")
//        text.text = intent.getStringExtra("itemText")
//    }

