package com.example.myapplication2


import ErrorDialogFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.databinding.FragmentItems2Binding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemsFragment : BaseFragment<FragmentItems2Binding>() {
    @set:Inject
    lateinit var itemsRepository: ItemsRepository
    private lateinit var itemsAdapter: ItemsAdapter
    private var itemList = mutableListOf<Item>()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItems2Binding = FragmentItems2Binding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchItems()
    }

    private fun setupRecyclerView() {
        itemsAdapter = ItemsAdapter(
            items = itemList,
            onItemClick = { item ->
                findNavController().navigate(
                    R.id.action_itemsFragment2_to_itemFragment2,
                    Bundle().apply {
                        putInt("itemId", item.id)
                        putString("itemTitle", item.name)
                        putString("itemText", item.description)
                    }
                )
            },
            context = requireContext()
        )
        binding.itemsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemsAdapter
        }
    }

    private fun fetchItems() {
        lifecycleScope.launch {
            try {
                val items = itemsRepository.getItems()
                itemList.clear()
                itemList.addAll(items)
                itemsAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                ErrorDialogFragment(e.message ?: "Неизвестная ошибка")
                .show(parentFragmentManager, "ErrordialogTitle")
            }
        }
    }
}



