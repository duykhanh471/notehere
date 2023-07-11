package vn.tofu.notehere.database


import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes :  LiveData<List<NoteData>> = noteDao.retrieveAllNote()

    suspend fun insertNote(note: NoteData) {
        noteDao.insertNote(note)
    }
    suspend fun updateNote(note: NoteData) {
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: NoteData) {
        noteDao.deleteNote(note)
    }

}