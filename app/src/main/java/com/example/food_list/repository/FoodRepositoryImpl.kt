package com.example.food_list.repository

import com.example.food_list.model.Food
import com.example.food_list.model.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodRepositoryImpl(private val foodDao: FoodDao) : FoodRepository {
    override suspend fun insertFoodList(foods: List<Food>) {
        withContext(Dispatchers.IO) {
            foodDao.insertFoodList(foods)
        }
    }
}