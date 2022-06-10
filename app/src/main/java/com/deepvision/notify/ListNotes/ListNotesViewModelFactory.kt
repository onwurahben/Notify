package com.deepvision.notify.ListNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deepvision.notify.RoomDB.LocalRepo

class ListNotesViewModelFactory(private val localRepo: LocalRepo) : ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {  //possible error in ViewModel?
            if (modelClass.isAssignableFrom(ListNotesViewModel::class.java)) {
                return ListNotesViewModel(localRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

