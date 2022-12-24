package com.example.food_list.utils

import com.example.food_list.FoodApplication
import com.example.food_list.R

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
object StringUtils {

    fun formatCaloriesString(calories: Short): String {
        return "$calories ${FoodApplication.instance.resources.getString(R.string.kcal)}"
    }
}