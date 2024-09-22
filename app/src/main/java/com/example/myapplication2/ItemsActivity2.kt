package com.example.myapplication2

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject


class ItemsActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items2)
        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()

        val jsonString =
            resources.openRawResource(R.raw.shopping_list).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            val item = Item(
//                id = jsonObject.getInt("id"),
//                image = jsonObject.getString("image"),
//                name = jsonObject.getString("name"),
//                description = jsonObject.getString("description"),
//                jsonObject.getString("additionalInfo1"),
//                jsonObject.getString("additionalInfo2"),
//                jsonObject.getString("additionalInfo3"),
//                price = jsonObject.getInt("price")
//            )
//            items.add(item)
//        }

        val itemList: List<Item> =
            Gson().fromJson(jsonString, object : TypeToken<List<Item>>() {}.type)
        val itemsAdapter = ItemsAdapter(itemList, context = this)

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = itemsAdapter
    }
}
