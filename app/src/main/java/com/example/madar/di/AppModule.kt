package com.example.madar.di

import android.content.Context
import androidx.room.Room
import com.example.madar.domain.usecases.user.GetAllUsers
import com.example.madar.domain.usecases.user.InsertUser
import com.example.madar.domain.repo.UserRepository
import com.example.madar.domain.usecases.user.UserUseCases
import com.example.madar.data.local.UserDao
import com.example.madar.data.local.UserDatabase
import com.example.madar.data.repo.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_db").build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(dao: UserDao): UserRepository = UserRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases = UserUseCases(
        insertUser = InsertUser(repository),
        getAllUsers = GetAllUsers(repository)
    )
}