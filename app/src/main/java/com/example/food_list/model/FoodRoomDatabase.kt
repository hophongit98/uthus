package com.example.food_list.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Database(entities = [Food::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class FoodRoomDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var instance: FoodRoomDatabase? = null

        fun getDatabase(context: Context): FoodRoomDatabase {
            return instance ?: synchronized(this) {
                val ins = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "food_database"
                ).build()
                instance = ins
                ins
            }
        }
    }
}