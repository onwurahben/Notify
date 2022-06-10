package com.deepvision.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet: BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view  = inflater.inflate(R.layout.bottom_sheet, container, false)

        val addNotes : LinearLayout = view.findViewById(R.id.school)

        val sketch = view.findViewById<LinearLayout>(R.id.shopping)

        addNotes.setOnClickListener{

          //  val navController = NavHostFragment.findNavController(this)
          //  navController.navigate(R.id.action_FirstFragment_to_createNoteFragment)

            requireActivity().findNavController(view.id).navigate(R.id.action_FirstFragment_to_createNoteFragment)
            dismiss()

        }

        sketch.setOnClickListener{
            //findNavController().navigate()

            requireActivity().findNavController(view.id).navigate(R.id.action_FirstFragment_to_sketchFragment)
            dismiss()

        }

        return view
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}