package com.example.food_list.dagger

import android.content.Context
import com.example.food_list.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}