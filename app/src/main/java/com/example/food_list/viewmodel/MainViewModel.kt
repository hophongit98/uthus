package com.example.food_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.food_list.MainActivityContract
import com.example.food_list.model.DataProvider
import com.example.food_list.model.Food
import com.example.food_list.repository.FoodRepository
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class MainViewModel : MainActivityContract.ViewModel() {

    private var _onUiChange = MutableLiveData<List<Food>>()
    override val onUiChange: LiveData<List<Food>> = _onUiChange

    private val foodItems = mutableListOf<Food>()
    private lateinit var foodRepository: FoodRepository

    init {
        if (foodItems.isEmpty()) {
            foodItems.addAll(DataProvider.provideDummyData())
        }
        _onUiChange.value = foodItems
    }

    override fun onCheckBoxClicked(itemId: String, isChecked: Boolean) {
        foodItems.find { it.id == itemId }?.let {
            it.isChecked = isChecked
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnAddClicked(itemId: String, count: Short) {
        foodItems.find { it.id == itemId }?.let {
            it.count = count
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnRemoveClicked(itemId: String, count: Short) {
        foodItems.find { it.id == itemId }?.let {
            it.count = count
            _onUiChange.value = foodItems
        }
    }

    override fun onBtnSaveClicked() {
        viewModelScope.launch {
            foodRepository.insertFoodList(foodItems)
        }
    }
}