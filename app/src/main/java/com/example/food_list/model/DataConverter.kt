package com.example.food_list.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class DataConverter {
    @TypeConverter
    fun fromFoodString(value: String?): List<Food>? {
        val listType = object : TypeToken<List<Food>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromFoodList(genres: List<Food>): String? {
        return Gson().toJson(genres)
    }
}