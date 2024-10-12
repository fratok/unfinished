package com.example.Unfinished

import android.content.ContentValues.TAG
import android.os.Bundle
import kotlinx.serialization.Serializable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.unfinished.R
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Serializable
data class Item(
    val id: Int,
    val image: String,
    val name: String,
    val description: String,
    val additionalInfo1: String,
    val additionalInfo2: String,
    val price: Int,
    val imageUrl: String
)
@Serializable
data class ItemList(
    val list: List<Item>
)

interface ItemApi{
    @GET("/JSON/shopping_list.json")
    suspend fun getItem(): Response<ItemList>
}
fun retrofitRequest(scope: CoroutineScope, onSuccess: (List<Item>) -> Unit, onFailure: (String) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fratok.github.io/")
        .addConverterFactory(GsonConverterFactory.create()) //указываем на использование Gson для работы с Json
        .build()

    val ItemListApi: ItemApi = retrofit.create(ItemApi::class.java)
    scope.launch(Dispatchers.IO) {
        try {
            val response = ItemListApi.getItem()
            if (response.isSuccessful) {
                val itemList = response.body() // получаем тело ответа
                if (itemList != null) {
                    Log.d(TAG, "Response body: $itemList")
                    onSuccess(itemList.list)
                } else {
                    onFailure("Response body is null")
                }
            } else {
                Log.e(TAG, "Failed: ${response.message()}")
                onFailure("Request failed: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Network error: ${e.message}")
            onFailure("Network error: ${e.message}")
        }
    }
}
class Main : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchItems() // Вызов функции для получения элементов
    }

    private fun fetchItems() {
        retrofitRequest(lifecycleScope, // Используем lifecycleScope для запуска корутины
            { items ->
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
}



