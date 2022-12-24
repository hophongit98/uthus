package com.example.food_list.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Entity
data class Food(
    @PrimaryKey val id: String,
    val name: String,
    val quantity: String,
    val calories: Short,
    val expiry: Long,
    var count: Short
) {
    constructor(food: Food) : this(
        id = food.id,
        name = food.name,
        quantity = food.quantity,
        calories = food.calories,
        expiry = food.expiry,
        count = food.count
    )
}