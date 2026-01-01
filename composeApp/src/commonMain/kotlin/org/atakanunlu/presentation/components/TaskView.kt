package org.atakanunlu.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.atakanunlu.domain.ToDoTask

@Composable
fun TaskView(
    task: ToDoTask,
    showActive: Boolean = true,
    onSelect: (ToDoTask) -> Unit,
    onComplete: (ToDoTask, Boolean) -> Unit,
    onFavorite: (ToDoTask, Boolean) -> Unit,
    onDelete: (ToDoTask) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (showActive) onSelect(task)
                else onDelete(task)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (showActive)
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (showActive) 2.dp else 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = { onComplete(task, !task.completed) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier.alpha(if (showActive) 1f else 0.6f),
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = if (showActive)
                        TextDecoration.None
                    else
                        TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = {
                    if (showActive) onFavorite(task, !task.favorite)
                    else onDelete(task)
                }
            ) {
                Icon(
                    imageVector = if (showActive) {
                        if (task.favorite) Icons.Filled.Star else Icons.Outlined.StarBorder
                    } else {
                        Icons.Default.Delete
                    },
                    contentDescription = if (showActive) "Favorite" else "Delete",
                    tint = if (showActive && task.favorite)
                        MaterialTheme.colorScheme.primary
                    else if (!showActive)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}