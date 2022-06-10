package com.deepvision.notify.FavoriteNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepvision.notify.Util.NOT_IS_STARRED
import com.deepvision.notify.Result.Success
import com.deepvision.notify.RoomDB.Notes
import com.deepvision.notify.RoomDB.LocalRepo
import kotlinx.coroutines.launch

class FavoriteNotesViewModel(private val localRepo: LocalRepo): ViewModel() {

    lateinit var notes: Result<List<Notes>>
    private lateinit var myFavorites : List<Notes>

    private val _favorites : MutableLiveData<List<Notes>> = getNotes()
    val favorites: LiveData<List<Notes>>
        get() = _favorites


    private fun getNotes():MutableLiveData<List<Notes>>{


        val values = MutableLiveData<List<Notes>>()
        viewModelScope.launch { notes = localRepo.getNotes()}

        if (notes is Success){

            myFavorites = (notes as Success<List<Notes>>).data

            values.value = getFavoriteNotes(myFavorites)
        }

        return values
    }

    private fun getFavoriteNotes(favoriteNotes: List<Notes>) : List<Notes> {

        val notes: List<Notes>? = null

        for (note in favoriteNotes){
            if (note.noteStatus == NOT_IS_STARRED){

                notes?.contains(note)
            }
        }

         return notes!!
    }


}
