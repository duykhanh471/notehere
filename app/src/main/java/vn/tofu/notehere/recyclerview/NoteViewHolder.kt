package vn.tofu.notehere.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.tofu.notehere.R
import vn.tofu.notehere.database.NoteData

class NoteViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
    private val noteTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val noteText: TextView = itemView.findViewById(R.id.tvText)

    fun bind(note: NoteData) {
        noteTitle.text = note.noteTitle
        noteText.text = note.noteText
    }
}