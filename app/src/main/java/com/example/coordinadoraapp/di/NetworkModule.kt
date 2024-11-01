package com.example.coordinadoraapp.di

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.coordinadoraapp.data.remote.AuthService
import com.example.coordinadoraapp.data.remote.AuthServiceImplementation
import com.example.coordinadoraapp.data.repositoryimple.AuthRepositoryImpl
import com.example.coordinadoraapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRequestQueue(@ApplicationContext context: Context): RequestQueue {
        return Volley.newRequestQueue(context)
    }

    @Provides
    @Singleton
    fun provideAuthService(requestQueue: RequestQueue): AuthService {
        return AuthServiceImplementation(requestQueue)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
}