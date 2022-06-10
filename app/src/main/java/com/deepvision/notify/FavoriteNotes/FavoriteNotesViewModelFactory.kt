package com.deepvision.notify.FavoriteNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deepvision.notify.ListNotes.ListNotesViewModel
import com.deepvision.notify.RoomDB.NotesRepo

class FavoriteNotesViewModelFactory(private val notesRepo: NotesRepo) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {  //possible error in ViewModel?
        if (modelClass.isAssignableFrom(FavoriteNotesViewModel::class.java)) {
            return ListNotesViewModel(notesRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
