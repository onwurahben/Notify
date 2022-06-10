package com.deepvision.notify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepvision.notify.Util.NOTE_ITEM_LOCKED
import com.deepvision.notify.Util.NOTE_OPEN
import com.deepvision.notify.RoomDB.Notes
import com.deepvision.notify.databinding.NoteItemLayoutBinding
import com.deepvision.notify.databinding.NoteItemLayoutLockedBinding
import com.google.android.material.card.MaterialCardView
import com.deepvision.notify.Adapters.NoteAdapter.NotesHolder


//This class becomes active post viewModel retrieving data from db
/**TODO --a viewHolder class takes the layout item from onCreateView and data for each item from onBindView and matches the two **/

class NoteAdapter : ListAdapter <Notes, NotesHolder> (NotesDiffCallback()) {

    var notes = ArrayList<Notes>() //not needed in ListAdapter


    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {

//        return if (viewType == NOTE_OPEN){


            val binding = NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return  NotesHolder(binding)


/*//        }else{
//            val binding = NoteItemLayoutLockedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            LockedNotesHolder(binding)
//        }*/
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */


    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val note = getItem(position)

        if (note != null) holder.bind(note)

//        when (note.noteSecurity){
//
//            note.isOpen ->
//                (holder as OpenNotesHolder).bind(note)
//            note.isLocked ->
//                (holder as LockedNotesHolder).bind(note)
//        }

    }


    class NotesHolder(
        private val binding: NoteItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {



        private var body = binding.noteContent.text.toString()
        private var noteCard: MaterialCardView = binding.noteItem
        var title: String = binding.noteTitle.text.toString()



        fun bind(note: Notes){
            note.title = title
            note.content = body

            if(note.noteSecurity == note.isLocked){

                binding.noteTitle.visibility  = View.GONE
                binding.noteContent.isVisible = false
            } else{
                binding.noteTitle.visibility  = View.VISIBLE
                binding.noteContent.visibility = View.VISIBLE
            }

        }


    }

  /*  class LockedNotesHolder(
        private val binding:NoteItemLayoutLockedBinding
    ):RecyclerView.ViewHolder(binding.root){

        private val noteCard = binding.noteItem

        fun bind(note: Notes){

        }

    }

*/
    class NotesDiffCallback : DiffUtil.ItemCallback<Notes>(){

        /**
         * Called to check whether two objects represent the same item.
         *
         *
         * For example, if your items have unique ids, this method should check their id equality.
         *
         *
         * Note: `null` items in the list are assumed to be the same as another `null`
         * item and are assumed to not be the same as a non-`null` item. This callback will
         * not be invoked for either of those cases.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the two items represent the same object or false if they are different.
         *
         * @see Callback.areItemsTheSame
         */

        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        /**
         * Called to check whether two items have the same data.
         *
         *
         * This information is used to detect if the contents of an item have changed.
         *
         *
         * This method to check equality instead of [Object.equals] so that you can
         * change its behavior depending on your UI.
         *
         *
         * For example, if you are using DiffUtil with a
         * [RecyclerView.Adapter], you should
         * return whether the items' visual representations are the same.
         *
         *
         * This method is called only if [.areItemsTheSame] returns `true` for
         * these items.
         *
         *
         * Note: Two `null` items are assumed to represent the same contents. This callback
         * will not be invoked for this case.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the contents of the items are the same or false if they are different.
         *
         * @see Callback.areContentsTheSame
         */

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

    }

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     *
     * The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * `position`. Type codes need not be contiguous.
     */
/*
    override fun getItemViewType(position: Int): Int {
        // val note = NotesDao.getNote(position)

        return when(notes[position].noteSecurity){     //TODO -- YOU CAN GET AN ITEM IN A LIST USING POSITION
            0 ->
                NOTE_OPEN
            1 ->
                NOTE_ITEM_LOCKED
            else -> -1
        }
    }
*/


    /*color = when {
        daysUntilDue <= 0 -> R.color.todoOverDue
        daysUntilDue <= 7 -> R.color.todoDueStrong
        daysUntilDue <= 14 -> R.color.todoDueMedium
        else -> R.color.todoDueLight
    }*/
}