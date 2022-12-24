package com.example.food_list.view

import android.view.View
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
            setPerformAction(btnRemove, item.count > 1)
            setPerformAction(btnAdd, true)

            tvCount.text = "${item.count}"

            cbChecked.setOnCheckedChangeListener { _, isChecked ->
                groupAction.isVisible = isChecked
                itemListener.onCheckBoxClicked(item.id, isChecked)
            }

            btnAdd.setOnClickListener {
                tvCount.text = "${++count}"
                itemListener.onBtnAddClicked(item.id, count)
                setPerformAction(btnRemove, true)
            }

            btnRemove.setOnClickListener {
                if (count > 1) {
                    tvCount.text = "${--count}"
                    itemListener.onBtnRemoveClicked(item.id, count)
                }
                setPerformAction(btnRemove, count > 1)
            }
        }
    }

    private fun setPerformAction(view: View, isAllowAction: Boolean) {
        with(view) {
            isEnabled = isAllowAction
            isClickable = isAllowAction
            isFocusable = isAllowAction
        }
    }
}