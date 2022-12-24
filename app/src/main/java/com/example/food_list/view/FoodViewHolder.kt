package com.example.food_list.view

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.MainActivityContract
import com.example.food_list.databinding.ItemFoodBinding
import com.example.food_list.model.Food
import com.example.food_list.utils.StringUtils

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodViewHolder(private val binding: ItemFoodBinding, private val itemListener: MainActivityContract.ItemListener) : RecyclerView.ViewHolder(binding.root) {
    var count: Short = 0

    fun bind(item: Food) {
        with(binding) {
            count = item.count
            tvName.text = item.name
            tvCalories.text = StringUtils.formatCaloriesString(item.calories)
            tvQuantity.text = item.quantity
            cbChecked.isChecked = item.isChecked
            groupAction.isVisible = item.isChecked
            tvCount.text = "${item.count}"

            cbChecked.setOnCheckedChangeListener { _, isChecked ->
                groupAction.isVisible = isChecked
                itemListener.onCheckBoxClicked(item.id, isChecked)
            }

            btnAdd.setOnClickListener {
                tvCount.text = "${++count}"
                itemListener.onBtnAddClicked(item.id, count)
            }

            btnRemove.setOnClickListener {
                tvCount.text = "${--count}"
                itemListener.onBtnRemoveClicked(item.id, count)
            }
        }
    }
}