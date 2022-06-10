package com.deepvision.notify.RoomDB

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.deepvision.notify.DataSource
import com.deepvision.notify.Result
import com.deepvision.notify.Result.Success
import com.deepvision.notify.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LocalRepo(private val notesDao: NotesDao) : DataSource {


    //Room automatically processes LiveData in a background thread hence no need for coroutine.

    override fun observeNotes(): LiveData<Result<List<Notes>>> {
        return notesDao.observeNotes().map {
            Success(it)
        }
    }

    override fun observeNote(notesId: String): LiveData<Result<Notes>> {
        return notesDao.observeNote(notesId).map {
            Success(it)
        }
    }

    override suspend fun getNotes(): Result<List<Notes>> = withContext(Dispatchers.IO){

        return@withContext try {
            Success(notesDao.getNotes())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getNote(notesId: String): Result<Notes> = withContext(Dispatchers.IO){

        return@withContext try {
            Success(notesDao.getNote(notesId))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun saveNote(note: Notes) {
        withContext(Dispatchers.IO){
            notesDao.saveNote(note)
        }
    }

    override suspend fun updateNote(note: Notes) {
        withContext(Dispatchers.IO){
            notesDao.updateNote(note)
        }
    }

    override suspend fun refreshNotes() {
        TODO("Not yet implemented")
    }

    override suspend fun refreshNotes(notesId: String) {
        TODO("Not yet implemented")
    }



    override suspend fun deleteAllNotes() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: String) {
        TODO("Not yet implemented")
    }

}