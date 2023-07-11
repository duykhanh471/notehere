package vn.tofu.notehere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_db")

data class NoteData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "noteTitle")
    var noteTitle: String,
    @ColumnInfo(name = "noteText")
    var noteText: String,
)
