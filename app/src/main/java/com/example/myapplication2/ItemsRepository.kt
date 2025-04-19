package com.example.myapplication2

import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ItemsRepository @Inject constructor(private val itemApi: ItemApi) {
    suspend fun getItems(): List<Item> {
        return try {
            val response = itemApi.getItems()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Список товаров пуст")
            } else {
                throw when (response.code()) {
                    404 -> Exception("Сервер не найден (ошибка 404)")
                    500 -> Exception("Ошибка сервера (500)")
                    else -> Exception("Ошибка сети: ${response.message()}")
                }
            }
        } catch (e: HttpException) {
            throw Exception("Ошибка HTTP: ${e.message}")
        } catch (e: IOException) {
            throw Exception("Нет соединения с интернетом")
        } catch (e: Exception) {
            throw Exception("Неизвестная ошибка: ${e.message}")
        }
    }
}



