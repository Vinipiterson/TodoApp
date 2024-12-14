package com.challange25.todolist.data

import kotlin.random.Random

data class ItemData(
    val title: String,
    val checked: Boolean = true,
    val id: Int = Random.nextInt(1, 1024),
)
