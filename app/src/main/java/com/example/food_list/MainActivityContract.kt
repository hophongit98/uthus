package com.example.food_list

import androidx.lifecycle.LiveData
import com.example.food_list.model.Food
import androidx.lifecycle.ViewModel as BaseViewModel

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
interface MainActivityContract {

    abstract class ViewModel: BaseViewModel() {
        abstract val onUiChange: LiveData<List<Food>>

        abstract fun onCheckBoxClicked()
        abstract fun onBtnAddClicked()
        abstract fun onBtnRemoveClicked()
    }
}