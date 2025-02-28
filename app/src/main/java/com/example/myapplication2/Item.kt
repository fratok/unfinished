package com.example.myapplication2

import ErrorDialogFragment
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
//fun retrofitRequest(scope: CoroutineScope, itemApi: ItemApi,  onSuccess: (List<Item>) -> Unit, onFailure: (String) -> Unit,) {
//    scope.launch(Dispatchers.IO){
//
//        try {
//            Log.d("TAG", "Запрос на получение элементов")
//            val response = itemApi.getItems()
//            Log.d("TAG", "ItemListApi: $response" )
//            if (response.isSuccessful) {
//                val items = response.body() // получаем тело ответа
//                if (items != null) {
//                    Log.d("TAG", "Response body: $items")
//                    onSuccess(items)
//                } else {
//                    onFailure("Response body is null")
//                }
//            } else {
//                Log.e("TAG", "Ошибка ответа: ${response.message()}")
//                onFailure("Ошибка: Не удалось получить данные с сервера. Пожалуйста, попробуйте позже.")
//            }
//        } catch (e: Exception) {
//            Log.e("TAG", "Ошибка сети: ${e.message}")
//            onFailure("Ошибка подключения. Проверьте ваше интернет-соединение и попробуйте снова.")
//        }
//    }
//}

class Main : DaggerActivity(), DialogListener {

    @Inject
    lateinit var itemApi: ItemApi


    private lateinit var loadingIndicator: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items2)

        loadingIndicator = findViewById(R.id.loadingIndicator)
        recyclerView = findViewById(R.id.itemsList)

        fetchItems()
    }

    private fun fetchItems() {
        loadingIndicator.visibility = View.VISIBLE
//        retrofitRequest(lifecycleScope,
//            { items ->
//                runOnUiThread {
//                    loadingIndicator.visibility = View.GONE
//                    val successDialog = ErrorDialogFragment("Данные загружены успешно!")
//                    successDialog.show(supportFragmentManager, "SuccessDialog")
//                    setupRecyclerView(items)
//                }
//            },
//            { errorMessage ->
//                runOnUiThread {
//                    loadingIndicator.visibility = View.GONE
//                    val errorDialog = ErrorDialogFragment(errorMessage)
//                    errorDialog.show(supportFragmentManager, "ErrorDialog")
//                }
//            }
//        )
    }

    private fun setupRecyclerView(items: List<Item>) {
        adapter = ItemsAdapter(items, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDialogDismissed() {
        Toast.makeText(this, "Диалог закрыт", Toast.LENGTH_SHORT).show()
    }
}



