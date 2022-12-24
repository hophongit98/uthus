package com.example.food_list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.MainActivityContract
import com.example.food_list.databinding.ItemFoodBinding

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodAdapter(private val listener: MainActivityContract.ItemListener) : ListAdapter<MainActivityContract.DisplayItem, FoodViewHolder>(FoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        if (itemCount > position && position != RecyclerView.NO_POSITION) {
            holder.stopCountdownExpiredTime()
            holder.bind(getItem(position))
        }
    }

}