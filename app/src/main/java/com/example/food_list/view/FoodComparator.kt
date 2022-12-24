package com.example.food_list.view

import androidx.recyclerview.widget.DiffUtil
import com.example.food_list.model.Food

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodComparator(private val newList: List<Food>, private val oldList: List<Food>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id ==  oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] ==  oldList[oldItemPosition]
    }
}