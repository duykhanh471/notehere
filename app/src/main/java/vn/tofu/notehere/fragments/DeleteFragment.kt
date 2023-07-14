package vn.tofu.notehere.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteData
import vn.tofu.notehere.database.NoteDatabase
import vn.tofu.notehere.database.NoteRepository
import vn.tofu.notehere.databinding.FragmentDeleteBinding
import vn.tofu.notehere.viewmodel.NoteViewModel
import vn.tofu.notehere.viewmodel.NoteViewModelFactory

class DeleteFragment : DialogFragment(R.layout.fragment_delete) {
    private lateinit var binding: FragmentDeleteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository
    private lateinit var currentNote: NoteData
    private val args: DeleteFragmentArgs by navArgs<DeleteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Windows layout
        if (dialog != null) dialog!!.window!!.setLayout(
            400.toPx(requireContext()),
            200.toPx(requireContext())
        )
        //init viewModel
        val noteDao = NoteDatabase.getDatabase(requireContext()).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
        //current note
        currentNote = args.note!!

        binding.confirmButton.setOnClickListener {
            noteViewModel.deleteNote(currentNote)
            dismiss()
        }
        binding.dismissButton.setOnClickListener {
            dismiss()
        }
    }

    private fun Int.toPx(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()

}