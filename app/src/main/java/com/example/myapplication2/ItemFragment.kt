package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.databinding.FragmentItem2Binding


class  ItemFragment : BaseFragment<FragmentItem2Binding>() {


    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItem2Binding = FragmentItem2Binding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            binding.itemListTitleOne.text = bundle.getString("itemTitle", "Название товара")
            binding.itemListText.text = bundle.getString("itemText", "Описание товаар")
        }

    }


}



