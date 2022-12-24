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
    var number: Short = 0

    fun bind(item: Food) {
        with(binding) {
            number = item.count
            tvName.text = item.name
            tvCalories.text = StringUtils.formatCaloriesString(item.calories)
            tvQuantity.text = item.quantity
            cbChecked.isChecked = item.isChecked
            groupAction.isVisible = item.isChecked
            setPerformAction(btnRemove, item.count > 1)
            setPerformAction(btnAdd, true)

            tvCount.text = "${item.count}"

            cbChecked.setOnClickListener {
                groupAction.isVisible = cbChecked.isChecked
                itemListener.onCheckBoxClicked(item.id, cbChecked.isChecked)
            }

            btnAdd.setOnClickListener {
                tvCount.text = "${++number}"
                itemListener.onBtnAddClicked(item.id, number)
                setPerformAction(btnRemove, true)
            }

            btnRemove.setOnClickListener {
                if (number > 1) {
                    tvCount.text = "${--number}"
                    itemListener.onBtnRemoveClicked(item.id, number)
                }
                setPerformAction(btnRemove, number > 1)
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