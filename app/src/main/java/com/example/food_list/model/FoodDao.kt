package com.example.food_list.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(movies: List<Food>)

    @Query("SELECT * FROM Food")
    suspend fun getMovieList(): List<Food>
}