package com.example.myapplication2

import retrofit2.Response
import retrofit2.http.GET
import kotlinx.serialization.SerialName
import com.example.myapplication2.ItemApi

interface ItemApi {
    @GET("/JSON/shopping_list.json")
    suspend fun getItems(): Response<List<Item>>
}