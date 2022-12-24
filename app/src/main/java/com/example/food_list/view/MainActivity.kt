package com.example.food_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_list.MainActivityContract
import com.example.food_list.R
import com.example.food_list.databinding.ActivityMainBinding
import com.example.food_list.model.Food
import com.example.food_list.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainActivityContract.ItemListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
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

    override fun onCheckBoxClicked(itemId: String, isChecked: Boolean) {
        viewModel.onCheckBoxClicked(itemId, isChecked)
    }

    override fun onBtnAddClicked(itemId: String, count: Short) {
        viewModel.onBtnAddClicked(itemId, count)
    }

    override fun onBtnRemoveClicked(itemId: String, count: Short) {
        viewModel.onBtnRemoveClicked(itemId, count)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_save -> {
            viewModel.onBtnSaveClicked()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.title = ""
    }

    private fun observeViewModel() {
        if (viewModel is MainViewModel) {
            viewModel.onUiChange.observe(this, ::updateUi)
        }
    }

    private fun updateUi(foodList: List<Food>) {
        if (foodList.isEmpty()) return

        binding.rvFoodList.adapter = FoodAdapter(this).apply { setData(foodList) }
    }
}