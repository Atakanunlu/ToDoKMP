package org.atakanunlu.presentation.screen.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.atakanunlu.domain.TaskAction
import org.atakanunlu.domain.ToDoTask

const val DEFAULT_TITLE = "Task Title"
const val DEFAULT_DESCRIPTION = "Add description..."

data class TaskScreen(val task: ToDoTask? = null) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()
        var currentTitle by remember {
            mutableStateOf(task?.title ?: "")
        }
        var currentDescription by remember {
            mutableStateOf(task?.description ?: "")
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        BasicTextField(
                            value = currentTitle,
                            onValueChange = { currentTitle = it },
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            ),
                            singleLine = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            decorationBox = { innerTextField ->
                                if (currentTitle.isEmpty()) {
                                    Text(
                                        text = DEFAULT_TITLE,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                    )
                                }
                                innerTextField()
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            floatingActionButton = {
                if (currentTitle.isNotEmpty() && currentDescription.isNotEmpty()) {
                    FloatingActionButton(
                        onClick = {
                            if (task != null) {
                                viewModel.setAction(
                                    action = TaskAction.Update(
                                        ToDoTask().apply {
                                            _id = task._id
                                            title = currentTitle
                                            description = currentDescription
                                        }
                                    )
                                )
                            } else {
                                viewModel.setAction(
                                    action = TaskAction.Add(
                                        ToDoTask().apply {
                                            title = currentTitle
                                            description = currentDescription
                                        }
                                    )
                                )
                            }
                            navigator.pop()
                        },
                        shape = RoundedCornerShape(16.dp),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    value = currentDescription,
                    onValueChange = { currentDescription = it },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    decorationBox = { innerTextField ->
                        Box {
                            if (currentDescription.isEmpty()) {
                                Text(
                                    text = DEFAULT_DESCRIPTION,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }
    }
}