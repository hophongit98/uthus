package com.example.food_list.model

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class Food(
    val id: String,
    val name: String,
    val quantity: String,
    val calories: Short,
    val expiry: Long,
    val isChecked: Boolean,
    val count: Short
)