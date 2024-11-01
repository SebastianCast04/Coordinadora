package com.example.coordinadoraapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class DataBaseUser: RoomDatabase() {
    abstract fun usuarioDao(): UserDao
}