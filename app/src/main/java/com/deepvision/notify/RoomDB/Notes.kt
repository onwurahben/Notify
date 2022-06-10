package com.deepvision.notify.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//First room table for notes

@Entity(tableName = "Notes")
data class Notes(


//    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notesId")
    var id:String = UUID.randomUUID().toString(),

    var title:String = " ",
    var content:String = " ",
    var date: String = " ",
    var noteLabel:String = " ",
    var noteSecurity: Int = 0,
    var noteStatus: Boolean = false){

    val titleForList: String
        get() = if (title.isNotEmpty()) title else content

    val isLocked
        get() = if(noteSecurity < 1) noteSecurity ++ else noteSecurity

    val isOpen
        get() = if(noteSecurity > 0) noteSecurity -- else noteSecurity


    val isEmpty
        get() = title.isEmpty() || content.isEmpty()

    val isFavorite
        get() = !noteStatus
}
