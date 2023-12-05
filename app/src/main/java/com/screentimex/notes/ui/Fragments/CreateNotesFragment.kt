package com.screentimex.notes.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.screentimex.notes.Models.Notes
import com.screentimex.notes.R
import com.screentimex.notes.ViewModel.NotesViewModel
import com.screentimex.notes.databinding.FragmentCreateNotesBinding
import java.util.Date

class CreateNotesFragment : Fragment() {

    lateinit var createNotesBinding : FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createNotesBinding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        createNotesBinding.apply {

            priorityGreen.setImageResource(R.drawable.baseline_done_24)

            priorityRed.setOnClickListener{
                priority = "3"
                priorityRed.setImageResource(R.drawable.baseline_done_24)
                priorityGreen.setImageResource(0)
                priorityYellow.setImageResource(0)
            }

            priorityYellow.setOnClickListener{
                priority = "2"
                priorityYellow.setImageResource(R.drawable.baseline_done_24)
                priorityGreen.setImageResource(0)
                priorityRed.setImageResource(0)
            }

            priorityGreen.setOnClickListener{
                priority = "1"
                priorityGreen.setImageResource(R.drawable.baseline_done_24)
                priorityRed.setImageResource(0)
                priorityYellow.setImageResource(0)
            }

            createNoteBtn.setOnClickListener{
                createNotes(it)
            }
        }

        return createNotesBinding.root
    }

    private fun createNotes(it: View?) {
        val title = createNotesBinding.title.text.toString()
        val subtitle = createNotesBinding.subTitle.text.toString()
        val text = createNotesBinding.text.text.toString()

        val date = Date()
        val currentDate : CharSequence = DateFormat.format("MMMM d , yyyy ",date.time )
        Log.e("@@@@@@", "onCreateView: $priority" )
        val string = validateNotes(title,subtitle,text)
        if ( string != "" ) {
            showToast("$string field is empty ")
        }else{
            val notes = Notes(null,title,subtitle,text,currentDate.toString(),priority)
            viewModel.addNotes(notes)
            showToast("Notes created succesfully")
            Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
        }

    }

    fun showToast(message : String){
        Toast.makeText(requireContext(),message , Toast.LENGTH_SHORT).show()
    }
    private fun validateNotes(title: String, subtitle: String, text: String) : String {
        var string = ""
        if( title.isBlank() ) string = "Title"
        else if ( subtitle.isBlank() ) string = "Subtitle"
        else if ( text.isBlank() ) string = "Text"

        return string
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Create Note")
    }

    private fun setToolbarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }
}