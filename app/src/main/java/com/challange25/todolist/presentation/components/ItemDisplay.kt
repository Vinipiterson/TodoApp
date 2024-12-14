package com.challange25.todolist.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challange25.todolist.data.ItemData
import com.challange25.todolist.ui.theme.TodoListTheme
import com.challange25.todolist.ui.theme.Typography
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDisplay(
    item: ItemData,
    onCheckedChange: ((Boolean) -> Unit),
    onDelete: () -> Unit
){
    val animationDuration = 500
    var isDismissed by remember {
        mutableStateOf(false)
    }
    val dismissState = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.StartToEnd){
                isDismissed = true
            }
            true
        }
    )

    LaunchedEffect(key1 = isDismissed) {
        if (isDismissed){
            delay(animationDuration.toLong())
            onDelete()
        }
    }

    SwipeToDismissBox(
        dismissState,
        backgroundContent = {},
        enableDismissFromEndToStart = false) {

        AnimatedVisibility(
            visible = !isDismissed,
            exit = shrinkVertically(
                animationSpec = tween(durationMillis = animationDuration),
                shrinkTowards = Alignment.Top
            ) + fadeOut()
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Text(text = item.title,
                        style = Typography.headlineSmall,
                        textDecoration = if (item.checked) TextDecoration.LineThrough else TextDecoration.None
                    )
                    Checkbox(checked = item.checked,
                        onCheckedChange = onCheckedChange
                    )
                }
            }
        }
    }
}