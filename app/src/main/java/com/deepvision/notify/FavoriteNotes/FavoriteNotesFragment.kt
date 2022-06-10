package com.deepvision.notify.FavoriteNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.deepvision.notify.Adapters.FavoritesAdapter
import com.deepvision.notify.Adapters.NoteAdapter
import com.deepvision.notify.ListNotes.ListNotesViewModel
import com.deepvision.notify.ListNotes.ListNotesViewModelFactory
import com.deepvision.notify.R
import com.deepvision.notify.RoomDB.AppDataBase
import com.deepvision.notify.RoomDB.LocalRepo
import com.deepvision.notify.databinding.FragmentCreateNoteBinding
import com.deepvision.notify.databinding.FragmentFavoritesBinding
import com.deepvision.notify.databinding.FragmentListNotesBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavoriteNotesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteNotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.toolbar.inflateMenu(R.menu.menu_main)

        /*binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
            }
        }**/
        //requireActivity().onNavigateUp()

        val mAdapter = FavoritesAdapter()
        binding.recyclerView.adapter = mAdapter

        val notesDao =
            AppDataBase.getInstance(requireContext()).notesDao //possible error in context part
        val notesRepo = LocalRepo(notesDao)
        val favoriteNotesViewModelFactory = FavoriteNotesViewModelFactory(notesRepo)

        viewModel =
            ViewModelProvider(this, favoriteNotesViewModelFactory)[FavoriteNotesViewModel::class.java]

        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            mAdapter.submitList(favorites)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}