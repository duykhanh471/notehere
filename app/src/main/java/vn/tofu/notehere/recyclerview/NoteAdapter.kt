package vn.tofu.notehere.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteData
import vn.tofu.notehere.fragments.HomeFragmentDirections



class NoteAdapter() : ListAdapter<NoteData, NoteViewHolder>(NoteComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return NoteViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)


        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateFragment2(currentNote)
            it.findNavController().navigate(direction)
        }
        holder.itemView.setOnLongClickListener {
            val secondDirections = HomeFragmentDirections.actionHomeFragmentToDeleteFragment(currentNote)
            it.findNavController().navigate(secondDirections)
            true
        }
    }
}

