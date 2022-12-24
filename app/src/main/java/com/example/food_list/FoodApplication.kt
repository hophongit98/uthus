package com.example.food_list

import android.app.Application
import com.example.food_list.dagger.AppComponent
import com.example.food_list.dagger.DaggerAppComponent

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
class FoodApplication: Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: FoodApplication
    }
}