package vn.tofu.notehere.recyclerview

import androidx.recyclerview.widget.DiffUtil
import vn.tofu.notehere.database.NoteData

class NoteComparator : DiffUtil.ItemCallback<NoteData>(){
    override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
        return oldItem.noteTitle == newItem.noteTitle
                && oldItem.noteText == newItem.noteText
    }


}
