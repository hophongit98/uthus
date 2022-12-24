package com.example.food_list.repository

import com.example.food_list.model.Food

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
interface FoodRepository {
    suspend fun getFoodList(): List<Food>
    suspend fun insertFoodList(foods: List<Food>)
}