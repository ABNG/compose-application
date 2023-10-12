package com.compose.app.di

import android.app.Application
import androidx.room.Room
import com.compose.app.data.local.CartDao
import com.compose.app.data.local.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCartDatabase(app: Application): CartDatabase {
        return Room.databaseBuilder(
            app,
            CartDatabase::class.java,
            "Carts"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCartDao(cartDatabase: CartDatabase): CartDao {
        return cartDatabase.cartDao
    }
}