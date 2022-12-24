package com.example.food_list.dagger

import android.content.Context
import com.example.food_list.MainActivityContract
import com.example.food_list.model.Food
import com.example.food_list.model.FoodDao
import com.example.food_list.model.FoodRoomDatabase
import com.example.food_list.repository.FoodRepository
import com.example.food_list.repository.FoodRepositoryImpl
import com.example.food_list.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Module
class AppModule {
    @Provides
    fun provideUserRepository(repository: FoodRepositoryImpl): FoodRepository = repository

    @Provides
    fun provideDatabase(context: Context): FoodRoomDatabase = FoodRoomDatabase.getDatabase(context)

    @Provides
    fun provideFoodDao(roomDatabase: FoodRoomDatabase): FoodDao = roomDatabase.foodDao()

    @Provides
    fun provideMovieListViewModel(viewModel: MainViewModel): MainActivityContract.ViewModel = viewModel
}