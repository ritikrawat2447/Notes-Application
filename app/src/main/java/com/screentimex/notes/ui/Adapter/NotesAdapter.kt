package com.screentimex.notes.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.screentimex.notes.Models.Notes
import com.screentimex.notes.R
import com.screentimex.notes.ViewModel.NotesViewModel
import com.screentimex.notes.databinding.ItemNotesBinding
import com.screentimex.notes.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val context : Context, var notesList : List<Notes> ) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filttering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    inner class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate( LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.Title.text = data.title
        holder.binding.subtitle.text = data.subtitle
        holder.binding.date.text = data.date

        when(data.priority){
            "1" -> holder.binding.priority.setBackgroundResource(R.drawable.green_dot)
            "2" -> holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)
            else -> holder.binding.priority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }
}