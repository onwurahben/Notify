package com.deepvision.notify.CreateNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deepvision.notify.RoomDB.LocalRepo

class CreateNoteViewModelFactory(private val localRepo: LocalRepo) : ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {  //possible error in ViewModel?
            if (modelClass.isAssignableFrom(CreateNoteViewModel::class.java)) {
                return CreateNoteViewModel(localRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }