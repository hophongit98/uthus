package com.example.food_list.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFoodList(movies: List<Food>)
}