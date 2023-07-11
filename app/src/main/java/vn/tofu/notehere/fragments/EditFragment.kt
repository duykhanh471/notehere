package vn.tofu.notehere.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteData
import vn.tofu.notehere.database.NoteDatabase
import vn.tofu.notehere.database.NoteRepository
import vn.tofu.notehere.databinding.FragmentEditBinding
import vn.tofu.notehere.viewmodel.NoteViewModel
import vn.tofu.notehere.viewmodel.NoteViewModelFactory

class EditFragment : Fragment(R.layout.fragment_edit) {
    private lateinit var binding: FragmentEditBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteDao = NoteDatabase.getDatabase(requireContext()).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        binding.editFab.setOnClickListener {
            saveNote()
            it.findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }

    }

    private fun saveNote() {
        val noteTitle = binding.etTitle.text.toString().trim()
        val noteText = binding.etNote.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val userNote = NoteData(0, noteTitle, noteText)
            noteViewModel.addNote(userNote)
        } else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

}