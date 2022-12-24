package com.example.food_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_list.MainActivityContract
import com.example.food_list.R
import com.example.food_list.databinding.ActivityMainBinding
import com.example.food_list.model.Food
import com.example.food_list.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                rvFoodList.apply {
                    layoutManager = LinearLayoutManager(
                        this@MainActivity.baseContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                }
            }
        observeViewModel()
    }

    private fun observeViewModel() {
        if (viewModel is MainViewModel) {
            viewModel.onUiChange.observe(this, ::updateUi)
        }
    }

    private fun updateUi(foodList: List<Food>) {
        if (foodList.isEmpty()) return

        binding.rvFoodList.adapter = FoodAdapter().apply { setData(foodList) }
    }
}