package com.deepvision.notify

import androidx.lifecycle.LiveData
import com.deepvision.notify.RoomDB.LocalRepo
import com.deepvision.notify.RoomDB.Notes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**THIS CLASS DECIDES WHAT DATA SOURCE TO USE.*/

class MainRepo(private val localRepo: LocalRepo) {

    suspend fun getNotes(forceUpdate: Boolean = false): Result<List<Notes>> {

//        if (forceUpdate) {
//            try {
//                updateTasksFromRemoteDataSource()
//            } catch (ex: Exception) {
//                return Result.Error(ex)
//            }
//        }

        return localRepo.getNotes()
    }



    fun observeNotesFromDb(): LiveData<Result<List<Notes>>> {
        return localRepo.observeNotes()
    }

    suspend fun refreshTask(taskId: String) {
        //updateTaskFromRemoteDataSource(taskId)
    }

    //TODO -- NOTE THAT EVERYTHING GOES INTO LOCAL DB FIRST

//    private suspend fun updateTasksFromRemoteDataSource() {
//        val remoteTasks = tasksRemoteDataSource.getTasks()
//
//        if (remoteTasks is Success) {
//            // Real apps might want to do a proper sync.
//            tasksLocalDataSource.deleteAllTasks()
//            remoteTasks.data.forEach { task ->
//                tasksLocalDataSource.saveTask(task)
//            }
//        } else if (remoteTasks is Result.Error) {
//            throw remoteTasks.exception
//        }
//    }


    fun observeNoteFromDb(noteId: String): LiveData<Result<Notes>> {
        return localRepo.observeNote(noteId)
    }

    suspend fun getNote(noteId: String,  forceUpdate: Boolean = false): Result<Notes> {
//        if (forceUpdate) {
//            updateTaskFromRemoteDataSource(taskId)
//        }
        return localRepo.getNote(noteId)
    }

    suspend fun saveNote(note: Notes) {
        coroutineScope {
           // launch { tasksRemoteDataSource.saveTask(task) }
            launch { localRepo.saveNote(note) }
        }
    }

    suspend fun updateNote(note: Notes) {

        coroutineScope {
            // launch { tasksRemoteDataSource.saveTask(task) }
            launch { localRepo.updateNote(note) }
        }
    }



    private suspend fun getNote(id: String): Result<Notes> {
        return localRepo.getNote(id)
    }


    suspend fun deleteAllTasks() {
//        withContext(Dispatchers.IO) {
//            coroutineScope {
//                launch { tasksRemoteDataSource.deleteAllTasks() }
//                launch { tasksLocalDataSource.deleteAllTasks() }
//            }
//        }
    }

    suspend fun deleteTask(taskId: String) {
//        coroutineScope {
//            launch { tasksRemoteDataSource.deleteTask(taskId) }
//            launch { tasksLocalDataSource.deleteTask(taskId) }
//        }
    }

}