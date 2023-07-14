package vn.tofu.notehere.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteData
import vn.tofu.notehere.database.NoteDatabase
import vn.tofu.notehere.database.NoteRepository
import vn.tofu.notehere.databinding.FragmentUpdateBinding
import vn.tofu.notehere.viewmodel.NoteViewModel
import vn.tofu.notehere.viewmodel.NoteViewModelFactory

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository
    private val args: UpdateFragmentArgs by navArgs<UpdateFragmentArgs>()
    private lateinit var currentNote: NoteData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //redundant
        val noteDao = NoteDatabase.getDatabase(requireContext()).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
        currentNote = args.note!!
        binding.updateTitle.setText(currentNote.noteTitle)
        binding.updateNote.setText(currentNote.noteText)

        binding.updateFab.setOnClickListener {
            updateNote()
            it.findNavController().navigate(R.id.action_updateFragment2_to_homeFragment)
        }

    }

    private fun updateNote() {
        val noteTitle = binding.updateTitle.text.toString().trim()
        val noteText = binding.updateNote.text.toString().trim()
        if (noteTitle.isNotEmpty()) {
            val userNote = NoteData(currentNote.id, noteTitle, noteText)
            noteViewModel.updateNote(userNote)
        } else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}