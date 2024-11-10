package com.example.myapplication2

import android.os.Bundle
import kotlinx.serialization.Serializable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName


@Serializable
data class Item(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("additionalInfo1")
    val additionalInfo1: String,
    @SerialName("additionalInfo2")
    val additionalInfo2: String,
    @SerialName("price")
    val price: Int,
    @SerialName("imageUrl")
    val imageUrl: String
)
@Serializable
data class ItemList(
    val list: List<Item>
)
@SerialName("id")

interface ItemApi{
    @GET("/JSON/shopping_list.json")
    suspend fun getItem(): Response<List<Item>>
}
fun retrofitRequest(scope: CoroutineScope, onSuccess: (List<Item>) -> Unit, onFailure: (String) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fratok.github.io/")
        .addConverterFactory(GsonConverterFactory.create()) //указываем на использование Gson для работы с Json
        .build()

    val ItemListApi: ItemApi = retrofit.create(ItemApi::class.java)
    scope.launch(Dispatchers.IO) {
        try {
            Log.d("TAG", "ItemListApi: $" )
            val response = ItemListApi.getItem()
            Log.d("TAG", "ItemListApi: $response" )
            if (response.isSuccessful) {

                val itemList = response.body() // получаем тело ответа
                if (itemList != null) {
                    Log.d("TAG", "Response body: $itemList")
                    onSuccess(itemList)
                } else {
                    onFailure("Response body is null")
                }
            } else {
                Log.e("TAG", "Ошибка ответа: ${response.message()}")
                onFailure("Error: Data could not be retrieved from the server. Please try again later")
            }
        } catch (e: Exception) {
            Log.e("TAG", "Ошибка сети: ${e.message}")
            onFailure("Connection error. Check your internet connection and try again")
        }
        }
    }

class Main : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}




