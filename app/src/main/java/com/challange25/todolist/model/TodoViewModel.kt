package com.challange25.todolist.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.challange25.todolist.data.ItemData

class TodoViewModel : ViewModel() {

    var dialogOpen by mutableStateOf(false)

    val tItems = mutableStateListOf<ItemData>()
}

val dummyItems = listOf<ItemData>(
    ItemData(title = "Meet bill gates", checked = false),
    ItemData(title = "Feed the 🐒", checked = false),
    ItemData(title = "Call Bin laden", checked = true),
)