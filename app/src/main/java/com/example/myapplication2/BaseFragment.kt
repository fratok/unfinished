package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewBinding> : DaggerFragment() {
    @Inject
    protected open lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _binding: T? = null
    protected val binding: T
        get() = _binding ?: throw IllegalStateException(
            "Binding не доступен. Обращение происходит вне жизненного цикла View"
        )
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
