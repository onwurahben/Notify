package com.deepvision.notify.CreateNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deepvision.notify.ADD_EDIT_RESULT_OK
import com.deepvision.notify.LabelDialogFragment
import com.deepvision.notify.Util.EventObserver
import com.deepvision.notify.R
import com.deepvision.notify.RoomDB.AppDataBase
import com.deepvision.notify.RoomDB.LocalRepo
import com.deepvision.notify.databinding.FragmentCreateNoteBinding


/**

 */
class CreateNoteFragment : Fragment() {

    // TODO-- A VAL CANNOT BE REASSIGNED BUT A VAR CAN BE REASSIGNED.

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateNoteViewModel
    private val args: CreateNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        binding.toolbar.inflateMenu(R.menu.menu_main)

        val notesDao =
            AppDataBase.getInstance(requireContext()).notesDao //possible error in context part
        val notesRepo = LocalRepo(notesDao)
        val createNoteViewModelFactory = CreateNoteViewModelFactory(notesRepo)

        viewModel =
            ViewModelProvider(this, createNoteViewModelFactory)[CreateNoteViewModel::class.java]

        val id = args.noteId

        viewModel.commence(id)
        setupNavigation()

        return binding.root
    }


    private fun setupNavigation() {
        viewModel.noteUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToFirstFragment(ADD_EDIT_RESULT_OK)
            findNavController().navigate(action)
        })

        viewModel.openDialog.observe(viewLifecycleOwner, EventObserver{
            val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToLabelDialogFragment()
            findNavController().navigate(action)
        })
    }

}