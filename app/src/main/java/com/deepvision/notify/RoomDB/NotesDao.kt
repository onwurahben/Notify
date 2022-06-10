package com.deepvision.notify.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes WHERE notesId = :id")
    fun observeNote(id: String) : LiveData<Notes>

    @Query("SELECT * FROM Notes ORDER BY notesId DESC")
    fun observeNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE notesId = :id")
    fun getNote(id: String) : Notes

    @Query("SELECT * FROM Notes ORDER BY notesId DESC")
    fun getNotes() : List<Notes>

    @Insert
    suspend fun saveNote(note: Notes)

    @Delete
    suspend fun deleteNotes(Notes: List<Notes>)

    @Delete
    suspend fun deleteNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Query("SELECT * FROM Notes WHERE noteStatus = :status ORDER BY notesId DESC")
    fun getFavoriteNotes(status : Boolean) : Notes

    @Query("UPDATE Notes SET noteStatus = :status WHERE notesId = :noteId")
    suspend fun addToFavorites(noteId: String, status: Boolean)

    @Query("UPDATE Notes SET noteSecurity = :status WHERE notesId = :noteId")
    suspend fun addToLocked(noteId: String, status: Int = 2)

    @Query("SELECT * FROM Notes WHERE noteStatus = :status ORDER BY notesId DESC")
    fun getLockedNotes(status : Int = 2) : Notes

}

