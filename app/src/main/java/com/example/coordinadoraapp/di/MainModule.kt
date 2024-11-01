package com.example.coordinadoraapp.di

import com.android.volley.RequestQueue
import com.example.coordinadoraapp.data.remote.AuthService
import com.example.coordinadoraapp.data.remote.MenuService
import com.example.coordinadoraapp.data.remote.MenuServiceImplementation
import com.example.coordinadoraapp.data.repositoryimple.MenuRepositoryImplementation
import com.example.coordinadoraapp.domain.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideMenuServiceImplementation (requestQueue: RequestQueue): MenuService {
        return MenuServiceImplementation(requestQueue)
    }

    @Provides
    @Singleton
    fun provideMenuRepositoryImplementation(menuService: MenuService):  MenuRepository{
        return MenuRepositoryImplementation(menuService)
    }
}