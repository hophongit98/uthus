package com.example.food_list.repository

import com.example.food_list.model.Food
import com.example.food_list.model.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Singleton
class FoodRepositoryImpl @Inject constructor(private val foodDao: FoodDao) : FoodRepository {
    override suspend fun getFoodList(): List<Food> {
        return foodDao.getMovieList()
    }

    override suspend fun insertFoodList(foods: List<Food>) {
        withContext(Dispatchers.IO) {
            foodDao.insertFoodList(foods)
        }
    }
}