package vn.tofu.notehere.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteDatabase
import vn.tofu.notehere.database.NoteRepository
import vn.tofu.notehere.databinding.FragmentHomeBinding
import vn.tofu.notehere.recyclerview.NoteAdapter
import vn.tofu.notehere.viewmodel.NoteViewModel
import vn.tofu.notehere.viewmodel.NoteViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Redundant
        val noteDao = NoteDatabase.getDatabase(requireContext()).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
        //Set up RecyclerView
        setUpRecyclerView()
        binding.addFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_editFragment)
        }
    }
    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.rvNote.adapter = noteAdapter
        binding.rvNote.layoutManager = LinearLayoutManager(context)
        displayNoteList()
    }

    private fun displayNoteList() {
        noteViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->
            notes?.let {
                noteAdapter.submitList(notes)
            }
        }
    }

}