package com.example.food_list

import android.app.Application

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodApplication: Application() {
    companion object {
        lateinit var instance: FoodApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}