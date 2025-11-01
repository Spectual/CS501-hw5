package com.example.dailyhub.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicInteger

data class NoteEntry(
    val id: Int,
    val text: String,
    val createdLabel: String
)

data class TaskItem(
    val id: Int,
    val title: String,
    val isDone: Boolean
)

class HubViewModel : ViewModel() {

    private val noteIdGenerator = AtomicInteger(0)
    private val taskIdGenerator = AtomicInteger(0)

    private val _notes = mutableStateListOf(
        NoteEntry(
            id = noteIdGenerator.getAndIncrement(),
            text = "Plan weekly study topics for CS501.",
            createdLabel = "Today"
        ),
        NoteEntry(
            id = noteIdGenerator.getAndIncrement(),
            text = "Brainstorm features for Daily Hub project.",
            createdLabel = "Yesterday"
        )
    )
    private val _tasks = mutableStateListOf(
        TaskItem(
            id = taskIdGenerator.getAndIncrement(),
            title = "Review lecture slides",
            isDone = false
        ),
        TaskItem(
            id = taskIdGenerator.getAndIncrement(),
            title = "Submit discussion post",
            isDone = true
        ),
        TaskItem(
            id = taskIdGenerator.getAndIncrement(),
            title = "Sync study calendar",
            isDone = false
        )
    )

    val notes: List<NoteEntry>
        get() = _notes

    val tasks: List<TaskItem>
        get() = _tasks

    var noteDraft by mutableStateOf("")
        private set

    var taskDraft by mutableStateOf("")
        private set

    fun onNoteDraftChanged(value: String) {
        noteDraft = value
    }

    fun onTaskDraftChanged(value: String) {
        taskDraft = value
    }

    fun addNote() {
        val text = noteDraft.trim()
        if (text.isEmpty()) return
        val note = NoteEntry(
            id = noteIdGenerator.getAndIncrement(),
            text = text,
            createdLabel = "Today"
        )
        _notes.add(0, note)
        noteDraft = ""
    }

    fun addTask() {
        val text = taskDraft.trim()
        if (text.isEmpty()) return
        val task = TaskItem(
            id = taskIdGenerator.getAndIncrement(),
            title = text,
            isDone = false
        )
        _tasks.add(0, task)
        taskDraft = ""
    }

    fun toggleTask(id: Int) {
        val index = _tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = _tasks[index]
            _tasks[index] = item.copy(isDone = !item.isDone)
        }
    }
}
