package com.screentimex.notes.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.screentimex.notes.Models.Notes
import com.screentimex.notes.R
import com.screentimex.notes.ViewModel.NotesViewModel
import com.screentimex.notes.databinding.FragmentEditNotesBinding
import java.util.Date

class EditNotesFragment : Fragment() {

    lateinit var editNotesBinding : FragmentEditNotesBinding
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        editNotesBinding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        editNotesBinding.apply {
            editTitle.setText(oldNotes.data.title.toString())
            editSubtitle.setText(oldNotes.data.subtitle.toString())
            editText.setText(oldNotes.data.note.toString())

            when(oldNotes.data.priority){
                "1" -> {
                    priority = "1"
                    editPriorityGreen.setImageResource(R.drawable.baseline_done_24)
                    editPriorityRed.setImageResource(0)
                    editPriorityYellow.setImageResource(0)
                }
                "2" -> {
                    priority = "2"
                    editPriorityYellow.setImageResource(R.drawable.baseline_done_24)
                    editPriorityGreen.setImageResource(0)
                    editPriorityRed.setImageResource(0)
                }
                else -> {
                    priority = "3"
                    editPriorityRed.setImageResource(R.drawable.baseline_done_24)
                    editPriorityGreen.setImageResource(0)
                    editPriorityYellow.setImageResource(0)
                }
            }

            editPriorityGreen.setOnClickListener{
                priority = "1"
                editPriorityGreen.setImageResource(R.drawable.baseline_done_24)
                editPriorityRed.setImageResource(0)
                editPriorityYellow.setImageResource(0)
            }
            editPriorityYellow.setOnClickListener{
                priority = "2"
                editPriorityYellow.setImageResource(R.drawable.baseline_done_24)
                editPriorityGreen.setImageResource(0)
                editPriorityRed.setImageResource(0)
            }
            editPriorityRed.setOnClickListener{
                priority = "3"
                editPriorityRed.setImageResource(R.drawable.baseline_done_24)
                editPriorityGreen.setImageResource(0)
                editPriorityYellow.setImageResource(0)
            }
        }

        editNotesBinding.editNoteBtn.setOnClickListener{
            updateNotes(it)
        }


        return editNotesBinding.root
    }

    private fun updateNotes(it: View?) {
        val title = editNotesBinding.editTitle.text.toString()
        val subtitle = editNotesBinding.editSubtitle.text.toString()
        val text = editNotesBinding.editText.text.toString()

        val date = Date()
        val currentDate : CharSequence = DateFormat.format("MMMM d , yyyy ",date.time )
        Log.e("@@@@@@", "onCreateView: $priority" )

        val notes = Notes(oldNotes.data.id,title,subtitle,text,currentDate.toString(),priority)
        viewModel.updateNotes(notes)
        Toast.makeText(requireContext(), "Notes updated successfully",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete ){
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.delete_dialog)
            bottomSheet.show()

            val postive_dlt = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val negative_dlt = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            postive_dlt?.setOnClickListener{
//                Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }

            negative_dlt?.setOnClickListener {
                bottomSheet.dismiss()
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Edit Note")
    }

    private fun setToolbarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }
}