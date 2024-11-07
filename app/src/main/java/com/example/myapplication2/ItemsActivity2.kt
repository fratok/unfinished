package com.example.myapplication2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class   ItemsActivity2 : AppCompatActivity() {
    var itemList = listOf<Item>()
    var itemsAdapter = ItemsAdapter(itemList, context = this)
    private lateinit var itemsList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items2)

        itemsList = findViewById(R.id.itemsList)
        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = itemsAdapter
        fetchItems()
    }
    private fun fetchItems() {
        retrofitRequest(lifecycleScope, // Используем lifecycleScope для запуска корутины
            { items ->
                itemList = items
                itemsAdapter = ItemsAdapter(itemList, context = this)
                itemsList.adapter = itemsAdapter

                // Обработка успешного ответа
                Log.d(TAG, "Получено ${items.size} элементов.")
                for (item in items) {
                    Log.d(TAG, "Item: ${item.name}, Price: ${item.price}")
                }
            },
            { errorMessage ->
                // Обработка ошибки
                Log.e(TAG, "Ошибка: $errorMessage")
            })
    }
    companion object{
        const val TAG = "ItemsActivity2"
    }
}

