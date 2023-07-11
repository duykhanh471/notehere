package vn.tofu.notehere.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: NoteData)
    @Update
    suspend fun updateNote(note: NoteData)
    @Delete
    suspend fun deleteNote(note: NoteData)
    @Query("SELECT * FROM note_db")
    fun retrieveAllNote() : LiveData<List<NoteData>>
    @Query("DELETE FROM note_db")
    suspend fun deleteAll()
}