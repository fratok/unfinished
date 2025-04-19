package com.example.myapplication2

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.myapplication2.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>() {
    val viewModel: TestViewModel by viewModels<TestViewModel>{  viewModelFactory }
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTestBinding = FragmentTestBinding.inflate(inflater, container, false)


}



