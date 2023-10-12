package com.compose.app.di

import com.compose.app.data.repository.AppPreferencesRepositoryImpl
import com.compose.app.data.repository.FirebaseAuthRepositoryImpl
import com.compose.app.data.repository.ProductDBRepositoryImpl
import com.compose.app.data.repository.ProductRepositoryImpl
import com.compose.app.domain.repository.AppPreferencesRepository
import com.compose.app.domain.repository.FirebaseAuthRepository
import com.compose.app.domain.repository.ProductDBRepository
import com.compose.app.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAppPreferencesRepository(appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl): AppPreferencesRepository

    @Singleton
    @Binds
    abstract fun bindFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository

    @Singleton
    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun bindProductDBRepository(productDBRepositoryImpl: ProductDBRepositoryImpl): ProductDBRepository
}