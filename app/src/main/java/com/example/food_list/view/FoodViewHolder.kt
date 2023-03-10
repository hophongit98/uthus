package com.example.food_list.view

import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.FoodApplication
import com.example.food_list.MainActivityContract
import com.example.food_list.R
import com.example.food_list.databinding.ItemFoodBinding
import com.example.food_list.utils.DateUtils
import com.example.food_list.utils.StringUtils

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodViewHolder(private val binding: ItemFoodBinding, private val itemListener: MainActivityContract.ItemListener) : RecyclerView.ViewHolder(binding.root) {
    private var number: Short = 0
    private var timer: CountDownTimer? = null

    fun bind(item: MainActivityContract.DisplayItem) {
        with(binding) {
            number = item.food.count
            tvName.text = item.food.name
            tvCalories.text = StringUtils.formatCaloriesString(item.food.calories)
            tvQuantity.text = item.food.quantity
            cbChecked.isChecked = item.isChecked
            groupAction.isVisible = item.isChecked
            setPerformAction(btnRemove, item.food.count > 1)
            setPerformAction(btnAdd, true)
            startCountdownExpiredTime(item.food.expiry)

            tvCount.text = "${item.food.count}"

            cbChecked.setOnClickListener {
                item.isChecked = cbChecked.isChecked
                groupAction.isVisible = cbChecked.isChecked
                itemListener.onCheckBoxClicked(item.food.id, cbChecked.isChecked)
            }

            btnAdd.setOnClickListener {
                tvCount.text = "${++number}"
                item.food.count = number
                itemListener.onBtnAddClicked(item.food.id, number)
                setPerformAction(btnRemove, true)
            }

            btnRemove.setOnClickListener {
                if (number > 1) {
                    tvCount.text = "${--number}"
                    item.food.count = number
                    itemListener.onBtnRemoveClicked(item.food.id, number)
                }
                setPerformAction(btnRemove, number > 1)
            }
        }
    }

    private fun setPerformAction(view: View, isAllowAction: Boolean) {
        if (view.isEnabled != isAllowAction) {
            with(view) {
                isEnabled = isAllowAction
                isClickable = isAllowAction
                isFocusable = isAllowAction
            }
        }
    }

    private fun startCountdownExpiredTime(date: Long) {
        timer = object : CountDownTimer(date - System.currentTimeMillis(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                with(binding) {
                    tvExpires.apply{
                        text = FoodApplication.instance.resources.getString(R.string.expires_in, DateUtils.getDurationBreakdown(millisUntilFinished))
                        setTextColor(ContextCompat.getColor(context, R.color.blue))
                    }
                    setPerformAction(cbChecked, true)
                }
            }

            override fun onFinish() {
                with(binding){
                    tvExpires.apply{
                        text = FoodApplication.instance.resources.getString(R.string.out_of_date)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                    setPerformAction(cbChecked, false)
                }
            }
        }
        timer?.start()
    }

    fun stopCountdownExpiredTime() {
        timer?.cancel()
    }
}