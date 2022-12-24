package com.example.food_list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.food_list.MainActivityContract
import com.example.food_list.model.DataProvider
import com.example.food_list.model.Food

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class MainViewModel : MainActivityContract.ViewModel() {

    private var _onUiChange = MutableLiveData<List<Food>>()
    override val onUiChange: LiveData<List<Food>> = _onUiChange

    init {
        _onUiChange.value = DataProvider.provideDummyData()
    }

    override fun onCheckBoxClicked(itemId: String, isChecked: Boolean) {
        Log.d("Phong", "onCheckBoxClicked - id=$itemId, isChecked=$isChecked")
    }

    override fun onBtnAddClicked(itemId: String, count: Short) {

    }

    override fun onBtnRemoveClicked(itemId: String, count: Short) {

    }
}