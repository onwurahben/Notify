package com.deepvision.notify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepvision.notify.Adapters.FavoritesAdapter.FavoritesHolder
import com.deepvision.notify.RoomDB.Notes
import com.deepvision.notify.databinding.NoteItemLayoutBinding


//This class becomes active post viewModel retrieving data from db


class FavoritesAdapter : ListAdapter <Notes, FavoritesHolder >(FavoritesDiffCallback()) {

    var notes = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {

        val binding = NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return  FavoritesHolder(binding)
    }


    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        val note = getItem(position)

        if (note != null && note.isFavorite) holder.bind(note)

    }


    class FavoritesHolder(
        private val binding: NoteItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val body = binding.noteContent.text.toString()
        private val title = binding.noteTitle.text.toString()

        fun bind (note: Notes){

            note.title = title                                     /** THIS IS THE ACTUAL BINDING */
            note.content = body

            if(note.noteSecurity == note.isLocked){

                binding.noteTitle.visibility  = View.GONE
                binding.noteContent.isVisible = false
            } else{
                binding.noteTitle.visibility  = View.VISIBLE
                binding.noteContent.visibility = View.VISIBLE
            }

           /* binding.apply {

                executePendingBindings()
            }*/

        }

    }


    class FavoritesDiffCallback : DiffUtil.ItemCallback<Notes>(){

        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

    }

    /* override fun getItemViewType(position: Int): Int {
         // val note = NotesDao.getNote(position)

         return if (notes[position].noteSecurity == 1){

             NOTE_OPEN
         } else

             NOTE_ITEM_LOCKED
     }*/

}