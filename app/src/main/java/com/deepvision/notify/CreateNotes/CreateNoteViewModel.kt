package com.deepvision.notify.CreateNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepvision.notify.Util.Event
import com.deepvision.notify.MainRepo
import com.deepvision.notify.Result.Success
import com.deepvision.notify.RoomDB.LocalRepo
import com.deepvision.notify.RoomDB.Notes
import kotlinx.coroutines.launch

class CreateNoteViewModel(private val localRepo: LocalRepo): ViewModel() {


    private var _openDialog = MutableLiveData<Event<Unit>>()
    val openDialog: LiveData<Event<Unit>> = _openDialog

    // Two-way databinding, exposing MutableLiveData
    val title = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val description = MutableLiveData<String>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    //this live data is updated in layout on click
    private val _noteUpdatedEvent = MutableLiveData<Event<Unit>>()
    val noteUpdatedEvent: LiveData<Event<Unit>> = _noteUpdatedEvent

    private var note = Notes()

    private var noteId: String? = null

    private var isNewNote: Boolean = false

    private var isDataLoaded = false

    private var taskCompleted = false

    private val mainRepo = MainRepo(localRepo)

    /** THIS METHOD IS PASSED NOTE ID FROM THE NAV ARGS IN FRAGMENT, THIS IS USED TO DETERMINE WHAT TO DISPLAY. */
    fun commence(noteId: String?) {
        if (_dataLoading.value == true) {
            return
        }

        this.noteId = noteId  //ViewModel's variable is assigned it's value
        if (noteId == null) {
            // No need to populate, it's a new task
            isNewNote = true
        }
        if (isDataLoaded) {
            // No need to populate, already have data.
            return
        }

        isNewNote = false
        _dataLoading.value = true

        viewModelScope.launch {
            mainRepo.getNote(noteId!!).let { result ->
                if (result is Success) {    // if it returns Result<T>
                    loadNote(result.data)
                } else {
                    onDataNotAvailable()
                }
            }
        }
    }

    private fun onDataNotAvailable() {
        _dataLoading.value = false
    }


    /**
     * USING DATA BINDING THESE VIEW MODEL FIELDS MUST BE LINKED TO THE LAYOUT
     * */
    private fun loadNote(note : Notes){
        title.value = note.title
        description.value = note.content
    }



    /** CALLED WHEN SAVE OR BACK BUTTON IS CLICKED */
    fun saveNote() {
        val currentTitle = title.value
        val currentDescription = description.value

        if (currentTitle == null || currentDescription == null) {
            //  _snackbarText.value = Event(R.string.empty_task_message)
            return
        }
        if (Notes(currentTitle, currentDescription).isEmpty) {
            //  _snackbarText.value = Event(R.string.empty_task_message)
            return
        }

        val currentNoteId = noteId
        if (isNewNote || currentNoteId  == null) {
            createNewNote(Notes(currentTitle, currentDescription))
        } else {
            val note = Notes(currentTitle, currentDescription, currentNoteId)
            updateNote(note)
        }
    }

    private fun updateNote(note: Notes) = viewModelScope.launch {

        if (isNewNote) {
            throw RuntimeException("updateTask() was called but task is new.")
        }
        mainRepo.updateNote(note)
        _noteUpdatedEvent.value = Event(Unit)
    }

    private fun createNewNote(newNote: Notes) = viewModelScope.launch {
        mainRepo.saveNote(newNote)
        _noteUpdatedEvent.value = Event(Unit)
    }

    fun openDialog(){
        _openDialog.value = Event(Unit)
    }


    /**IF THIS DOES NOT WORK THEN MAKE USE OF DIFFERENT METHODS*/
    fun setNoteLabel(noteLabel : Int){

        when(noteLabel){
            1 -> note.noteLabel = "Work"
            2 -> note.noteLabel = "School"
            3 -> note.noteLabel = "Shopping"
            4 -> note.noteLabel = "Health"
            5 -> note.noteLabel = "Random"
        }
    }







//    private fun readDisplayStateValues() {
//        val intent: Intent = getIntent()
//        val position = intent.getIntExtra(
//            com.jwhh.notekeeper.NoteActivity.NOTE_POSITION,
//            com.jwhh.notekeeper.NoteActivity.POSITION_NOT_SET
//        )
//        mIsNewNote = position == com.jwhh.notekeeper.NoteActivity.POSITION_NOT_SET
//        if (mIsNewNote) {
//            createNewNote()
//        } else {
//            mNote = DataManager.getInstance().getNotes().get(position)
//        }
//    }
//
//    private fun createNewNote() {
//        val dm: DataManager = DataManager.getInstance()
//        mNotePosition = dm.createNewNote()
//        mNote = dm.getNotes().get(mNotePosition)
//    }

}