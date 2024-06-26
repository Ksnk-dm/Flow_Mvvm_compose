package com.dm.flow_mvvm.di

import android.content.Context
import androidx.room.Room
import com.dm.flow_mvvm.repository.ItemRepository
import com.dm.flow_mvvm.room.AppDatabase
import com.dm.flow_mvvm.room.ItemDao
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
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "item_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideItemDao(appDatabase: AppDatabase): ItemDao = appDatabase.itemDao()

    @Provides
    fun provideItemRepository(itemDao: ItemDao): ItemRepository = ItemRepository(itemDao)
}