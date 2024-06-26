package com.dm.flow_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.flow_mvvm.repository.ItemRepository
import com.dm.flow_mvvm.room.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository): ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allItems.collect { listOfItems ->
                _items.value = listOfItems
            }
        }
    }
    
    fun insert(item: Item) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: Item) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        repository.delete(item)
    }
}