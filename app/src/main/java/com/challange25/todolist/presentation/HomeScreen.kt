package com.challange25.todolist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.challange25.todolist.data.ItemData
import com.challange25.todolist.model.TodoViewModel
import com.challange25.todolist.model.dummyItems
import com.challange25.todolist.presentation.components.AddButton
import com.challange25.todolist.presentation.components.AddDialog
import com.challange25.todolist.presentation.components.ItemDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TodoViewModel = viewModel()
){
    // Remove to not include dummy items
    viewModel.tItems += dummyItems

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(title = "Todo List", scrollBehavior = scrollBehavior) },
        floatingActionButton = { AddButton(onClick = {viewModel.dialogOpen = true}) }
    ) { innerPadding ->

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            if (viewModel.tItems.isEmpty()){
                item {
                    Text(
                        text = "You don't have any todo",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }else {
                items(items = viewModel.tItems, key = { it.id }){ tItem ->
                    ItemDisplay(item = tItem,
                        onCheckedChange = { newValue ->
                            viewModel.tItems[viewModel.tItems.indexOf(tItem)] = tItem.copy(checked = newValue)
                        },
                        onDelete = {
                            viewModel.tItems -= tItem
                        }
                    )
                }
            }
        }

        when { viewModel.dialogOpen ->
            AddDialog(
                onDismissRequest = { viewModel.dialogOpen = false },
                onConfirmation = { title ->
                    viewModel.tItems.add(ItemData(title = title, checked = false))
                    viewModel.dialogOpen = false
                }
            )
        }
    }
}