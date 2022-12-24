package com.example.food_list.view

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.databinding.ItemFoodBinding
import com.example.food_list.model.Food
import com.example.food_list.utils.StringUtils

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Food) {
        with(binding) {
            tvName.text = item.name
            tvCalories.text = StringUtils.formatCaloriesString(item.calories)
            tvQuantity.text = item.quantity
            groupAction.isVisible = cbChecked.isChecked
        }
    }
}