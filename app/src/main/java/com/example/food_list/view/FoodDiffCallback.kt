package com.example.food_list.view

import androidx.recyclerview.widget.DiffUtil
import com.example.food_list.MainActivityContract
import com.example.food_list.model.Food

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodDiffCallback : DiffUtil.ItemCallback<MainActivityContract.DisplayItem>() {
    override fun areItemsTheSame(
        oldItem: MainActivityContract.DisplayItem,
        newItem: MainActivityContract.DisplayItem
    ): Boolean {
        return oldItem.food.id == newItem.food.id
    }

    override fun areContentsTheSame(
        oldItem: MainActivityContract.DisplayItem,
        newItem: MainActivityContract.DisplayItem
    ): Boolean {
        return oldItem == newItem
    }
}