package com.example.myapplication2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.databinding.ActivityItems2Binding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ItemsFragment2 : BaseFragment<ActivityItems2Binding>() {
    @Inject
    lateinit var itemApi: ItemApi

    private lateinit var itemsAdapter: ItemsAdapter
    private var itemList = mutableListOf<Item>()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActivityItems2Binding = ActivityItems2Binding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()
        fetchItems()
    }

    private fun setupRecyclerView() {
        itemsAdapter = ItemsAdapter(itemList, requireContext())
        binding.itemsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemsAdapter
        }
    }

    private fun fetchItems() {
//        retrofitRequest(
//           scope =  viewLifecycleOwner.lifecycleScope,
//            itemApi = itemApi,
//            onSuccess = { items ->
//                lifecycleScope.launch(Dispatchers.Main) {
//                   itemList.clear()
//                    itemList.addAll(items)
//                    itemsAdapter.notifyDataSetChanged()
//                    Log.d(TAG, "Получено ${items.size} элементов.")
//
//                    for (item in items) {
//                        Log.d(TAG, "Item: ${item.name}, Price: ${item.price}")
//                   }
//                }
//           },
//            onFailure = { errorMessage ->
////                lifecycleScope.launch(Dispatchers.Main) {
////                    Log.e(TAG, "Ошибка: $errorMessage")
////                    val errorDialog = ErrorDialogFragment(errorMessage)
////                    ErrorDialogFragment(errorMessage).show(supportFragmentManager, "ErrorDialog")
////               }
////            }
////       )
////    }
    }

    companion object {
        const val TAG = "ItemsActivity2"
    }
}
