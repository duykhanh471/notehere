package vn.tofu.notehere.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.tofu.notehere.database.NoteData
import vn.tofu.notehere.database.NoteRepository

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    fun addNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }
    fun deleteNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }
    fun updateNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }
    fun getAllNotes() = repository.allNotes
}