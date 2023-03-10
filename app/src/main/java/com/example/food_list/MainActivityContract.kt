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
        abstract val onUiChange: LiveData<List<DisplayItem>>

        abstract fun onCheckBoxClicked(itemId: String, isChecked: Boolean)
        abstract fun onBtnAddClicked(itemId: String, count: Short)
        abstract fun onBtnRemoveClicked(itemId: String, count: Short)
        abstract fun onBtnSaveClicked()
    }

    interface ItemListener {
        fun onCheckBoxClicked(itemId: String, isChecked: Boolean)
        fun onBtnAddClicked(itemId: String, count: Short)
        fun onBtnRemoveClicked(itemId: String, count: Short)
    }

    data class DisplayItem(
        val food: Food,
        var isChecked: Boolean
    )
}