package com.deepvision.notify.ListNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deepvision.notify.Adapters.NoteAdapter
import com.deepvision.notify.Util.EventObserver
import com.deepvision.notify.R
import com.deepvision.notify.RoomDB.AppDataBase
import com.deepvision.notify.RoomDB.LocalRepo
import com.deepvision.notify.databinding.FragmentListNotesBinding
import com.deepvision.notify.databinding.NoteItemLayoutBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null

    // This property is only valid between onCreateView and onDestroyView.

    private val binding get() = _binding!!
    private lateinit var viewModel: ListNotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListNotesBinding.inflate(inflater, container, false)

        binding.toolbar.inflateMenu(R.menu.menu_main)

        /*binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
            }
        }**/
        //requireActivity().onNavigateUp()

        setUpListAdapter()
        setupNavigation()

        return binding.root
    }

    private fun setUpListAdapter() {
        val mAdapter = NoteAdapter()

        binding.apply { this.viewmodel = viewModel}
        binding.recyclerView.adapter = mAdapter

        val notesDao = AppDataBase.getInstance(requireContext()).notesDao //possible error in context
        val notesRepo = LocalRepo(notesDao)
        val listNotesViewModelFactory = ListNotesViewModelFactory(notesRepo)

        viewModel = ViewModelProvider(this, listNotesViewModelFactory)[ListNotesViewModel::class.java]

        viewModel.items.observe(viewLifecycleOwner) { myNotes ->

            mAdapter.submitList(myNotes)
        }
    }


    private fun setupNavigation() {
        viewModel.openNoteEvent.observe(viewLifecycleOwner, EventObserver { NoteId ->
            openNoteDetails(NoteId)
        })
    }

    private fun openNoteDetails(id: String) {
        val action = ListNotesFragmentDirections.actionFirstFragmentToCreateNoteFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


//    private fun setupNavigation() {
//        viewModel.openTaskEvent.observe(viewLifecycleOwner, EventObserver {
//            openTaskDetails(it)
//        })
//        viewModel.newTaskEvent.observe(viewLifecycleOwner, EventObserver {
//            navigateToAddNewTask()
//        })
//    }


//    private fun setupFab() {
//        viewDataBinding.addTaskFab.setOnClickListener {
//            navigateToAddNewTask()
//        }
//    }
//
//    private fun navigateToAddNewTask() {
//        val action = TasksFragmentDirections
//            .actionTasksFragmentToAddEditTaskFragment(
//                null,
//                resources.getString(R.string.add_task)
//            )
//        findNavController().navigate(action)
//    }

}






