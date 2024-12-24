package com.example.myapplication2

import kotlinx.serialization.Serializable
@Serializable
data class ItemList(
    val list: List<Item>
)
