package com.challange25.todolist.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(
    onClick: () -> Unit
){
    FloatingActionButton(onClick = { onClick() }, modifier = Modifier.padding(20.dp)) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}