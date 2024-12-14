package com.challange25.todolist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.challange25.todolist.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit
){
    var itemName by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }

    val dismissButton: (@Composable () -> Unit) = {
        TextButton(onClick = {onDismissRequest()}) {
            Text("Cancel")
        }
    }
    var confirmButton : (@Composable () -> Unit) = {
        TextButton(onClick = {
            isError = itemName.isBlank()
            if (!isError) onConfirmation(itemName)
            }
        ) {
            Text("Add")
        }
    }

    AlertDialog(onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        icon = {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
        },
        title = { Text(text = "Add Item")},
        text = {
            OutlinedTextField(
                value = itemName,
                onValueChange = {itemName = it},
                label = {Text(text = "Item name")},
                singleLine = true,
                isError = isError,
                supportingText = {
                    if (isError){
                        Text("*Item name is null")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        isError = itemName.isBlank()
                        if (!isError) onConfirmation(itemName)
                    }
                )
            )
        }
    )
}