package com.deepvision.notify.ListNotes

import androidx.lifecycle.*
import com.deepvision.notify.Util.Event
import com.deepvision.notify.MainRepo
import com.deepvision.notify.Result
import com.deepvision.notify.RoomDB.Notes
import kotlinx.coroutines.launch
import com.deepvision.notify.Result.Success


class ListNotesViewModel(private val mainRepo: MainRepo):ViewModel() {

    var notes =
        viewModelScope.launch { mainRepo.observeNotesFromDb() }//LiveData objects from db, needs to be transformed

    private val _openNoteEvent = MutableLiveData<Event<String>>()
    val openNoteEvent: LiveData<Event<String>> = _openNoteEvent

    //Called in XML onclick
    fun openNote(noteId: String) {
        _openNoteEvent.value = Event(noteId)
    }

    private val _notes: LiveData<List<Notes>> =
        mainRepo.observeNotesFromDb().switchMap { filterNotes(it) }

    val items: LiveData<List<Notes>> = _notes


    private fun filterNotes(noteResult: Result<List<Notes>>): LiveData<List<Notes>> {

        val result = MutableLiveData<List<Notes>>()

        if (noteResult is Success) {
            //  isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = noteResult.data!!
            }
        }

        return result

    }

}