package com.example.dailyhub.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.dailyhub.data.NoteEntry
import com.example.dailyhub.data.TaskItem

@Composable
fun NotesScreen(
    notes: List<NoteEntry>,
    draft: String,
    onDraftChanged: (String) -> Unit,
    onAddNote: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = draft,
                onValueChange = onDraftChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("New note") },
                placeholder = { Text("Capture what's on your mind…") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onAddNote,
                    enabled = draft.isNotBlank()
                ) {
                    Text(text = "Add note")
                }
            }
        }
        items(notes, key = { it.id }) { note ->
            NoteCard(entry = note)
        }
    }
}

@Composable
private fun NoteCard(entry: NoteEntry, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = entry.createdLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = entry.text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun TasksScreen(
    tasks: List<TaskItem>,
    draft: String,
    onDraftChanged: (String) -> Unit,
    onAddTask: () -> Unit,
    onToggleTask: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = "Tasks",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = draft,
                onValueChange = onDraftChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("New task") },
                placeholder = { Text("Add a to-do item…") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onAddTask,
                    enabled = draft.isNotBlank()
                ) {
                    Text(text = "Add task")
                }
            }
        }
        items(tasks, key = { it.id }) { task ->
            TaskRow(
                task = task,
                onToggle = { onToggleTask(task.id) }
            )
        }
    }
}

@Composable
private fun TaskRow(task: TaskItem, onToggle: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = if (task.isDone) FontWeight.Medium else FontWeight.Normal,
                        textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
                    )
                )
                AnimatedVisibility(visible = task.isDone) {
                    Text(
                        text = "Completed",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calendar",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Calendar sync coming soon!",
            style = MaterialTheme.typography.bodyLarge
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "• 10:00 AM – Design review\n• 2:00 PM – Study lab\n• 7:00 PM – Workout",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
