package com.example.food_list.view

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.FoodApplication
import com.example.food_list.MainActivityContract
import com.example.food_list.R
import com.example.food_list.databinding.ItemFoodBinding
import com.example.food_list.model.Food
import com.example.food_list.utils.DateUtils
import com.example.food_list.utils.StringUtils

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodViewHolder(private val binding: ItemFoodBinding, private val itemListener: MainActivityContract.ItemListener) : RecyclerView.ViewHolder(binding.root) {
    private var number: Short = 0
    private var timer: CountDownTimer? = null

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
            startCountdownExpiredTime(item.expiry)

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

    private fun startCountdownExpiredTime(date: Long) {
        timer = object : CountDownTimer(date - System.currentTimeMillis(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvExpires.text = FoodApplication.instance.resources.getString(R.string.expires_in, DateUtils.getDurationBreakdown(millisUntilFinished))
            }

            override fun onFinish() {
                binding.tvExpires.text = FoodApplication.instance.resources.getString(R.string.out_of_date)
            }
        }
        timer?.start()
    }

    fun stopCountdownExpiredTime() {
        timer?.cancel()
    }
}