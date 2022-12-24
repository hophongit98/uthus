package com.example.food_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.food_list.MainActivityContract
import com.example.food_list.model.DataProvider
import com.example.food_list.model.Food
import com.example.food_list.repository.FoodRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class MainViewModel @Inject constructor(private val foodRepository: FoodRepository) : MainActivityContract.ViewModel() {

    private var _onUiChange = MutableLiveData<List<MainActivityContract.DisplayItem>>()
    override val onUiChange: LiveData<List<MainActivityContract.DisplayItem>> = _onUiChange

    private val foodItems = mutableListOf<MainActivityContract.DisplayItem>()

    init {
        viewModelScope.launch {
            val list = foodRepository.getFoodList()
            if (list.isEmpty()) {
                foodItems.addAll(convertToDisplayItems(DataProvider.provideDummyData()))
            } else {
                foodItems.addAll(convertToDisplayItems(list))
            }
            _onUiChange.value = foodItems
        }
    }

    override fun onCheckBoxClicked(itemId: String, isChecked: Boolean) {
        foodItems.find { it.food.id == itemId }?.let {
            it.isChecked = isChecked
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnAddClicked(itemId: String, count: Short) {
        foodItems.find { it.food.id == itemId }?.let {
            it.food.count = count
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnRemoveClicked(itemId: String, count: Short) {
        foodItems.find { it.food.id == itemId }?.let {
            it.food.count = count
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnSaveClicked() {
        viewModelScope.launch {
            foodRepository.insertFoodList(convertToFoodList(foodItems))
        }
    }

    private fun convertToDisplayItems(foodItems: List<Food>): List<MainActivityContract.DisplayItem> {
        return foodItems.map {
            MainActivityContract.DisplayItem(it, false)
        }
    }

    private fun convertToFoodList(displayItems: List<MainActivityContract.DisplayItem>): List<Food> {
        return displayItems.map { Food(it.food) }
    }
}