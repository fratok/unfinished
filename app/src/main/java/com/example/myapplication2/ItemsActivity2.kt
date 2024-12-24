package com.example.myapplication2


import ErrorDialogFragment
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class   ItemsActivity2 : AppCompatActivity() {
    var itemList = mutableListOf<Item>()
    var itemsAdapter = ItemsAdapter(itemList, context = this)
    private lateinit var itemsList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items2)

        itemsList = findViewById(R.id.itemsList)
        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = itemsAdapter
        itemsAdapter = ItemsAdapter(itemList, context = this)
        fetchItems()
    }


    private fun fetchItems() {
        retrofitRequest(lifecycleScope, // Используем lifecycleScope для запуска корутины
            { items ->
                lifecycleScope.launch(Dispatchers.Main) {
                    itemList.clear()
                    itemList.addAll(items)
                    itemsAdapter = ItemsAdapter(itemList, context = this@ItemsActivity2)
                    itemsList.adapter = itemsAdapter

                    // Обработка успешного ответа
                    Log.d(TAG, "Получено ${items.size} элементов.")
                    for (item in items) {
                        Log.d(TAG, "Item: ${item.name}, Price: ${item.price}")
                    }
                }
            },
            { errorMessage ->
                lifecycleScope.launch(Dispatchers.Main) {

                    // Обработка ошибки
                    Log.e(TAG, "Ошибка: $errorMessage")
                    val errorDialog = ErrorDialogFragment(errorMessage)
                    errorDialog.show(supportFragmentManager, "ErrorDialog")
                }
            }
        )
    }

    companion object {
        const val TAG = "ItemsActivity2"
    }
}
