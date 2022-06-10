package com.deepvision.notify

import androidx.lifecycle.LiveData
import com.deepvision.notify.RoomDB.Notes
import com.deepvision.notify.Util.Result

//Must be implemented by all data sources

interface DataSource {


    fun observeNotes(): LiveData<Result<List<Notes>>>

    suspend fun getNotes(): Result<List<Notes>>

    suspend fun refreshNotes()

    fun observeNote(notesId: String): LiveData<Result<Notes>>

    suspend fun getNote(notesId: String): Result<Notes>

    suspend fun refreshNotes(notesId: String)

    suspend fun saveNote(note: Notes)

    suspend fun updateNote(note: Notes)

//    suspend fun completeTask(note: Notes)
//
//    suspend fun completeTask(taskId: String)
//
//    suspend fun activateTask(task: Task)
//
//    suspend fun activateTask(taskId: String)
//
//    suspend fun clearCompletedTasks()

    suspend fun deleteAllNotes()

    suspend fun deleteNote(noteId: String)

}