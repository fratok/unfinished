package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewBinding> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private var _binding: T? = null
    val binding = _binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflateViewBinding(inflater, container).also{_binding = it}.root
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): T
}
