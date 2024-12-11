package com.example.myapplication2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("additionalInfo1")
    val additionalInfo1: String?,
    @SerialName("additionalInfo2")
    val additionalInfo2: String?,
    @SerialName("price")
    val price: Double,
    @SerialName("imageUrl")
    val imageUrl: String
)