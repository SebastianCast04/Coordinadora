package com.example.coordinadoraapp.di

import android.content.Context
import androidx.room.Room
import com.example.coordinadoraapp.data.local.database.DataBaseUser
import com.example.coordinadoraapp.data.local.database.UserDao
import com.example.coordinadoraapp.data.repositoryimple.UserRepositoryImpl
import com.example.coordinadoraapp.domain.repository.AuthRepository
import com.example.coordinadoraapp.domain.repository.UserRepository
import com.example.coordinadoraapp.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase = LoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DataBaseUser {
        return Room.databaseBuilder(
            context,
            DataBaseUser::class.java,
            "usuario_db"
        ).build()
    }

    @Provides
    fun provideUserDao(db: DataBaseUser): UserDao {
        return db.usuarioDao()
    }

    @Provides
    fun provideUserRepository(dao: UserDao): UserRepository {
        return UserRepositoryImpl(dao)
    }
}