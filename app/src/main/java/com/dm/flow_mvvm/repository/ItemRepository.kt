package com.dm.flow_mvvm.repository

import com.dm.flow_mvvm.room.Item
import com.dm.flow_mvvm.room.ItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao) {

    val allItems: Flow<List<Item>> =
        itemDao.getAllItems()

    suspend fun insert(item: Item) =
        itemDao.insertItem(item)

    suspend fun update(item: Item) =
        itemDao.updateItem(item)

    suspend fun delete(item: Item) =
        itemDao.deleteItem(item)
}