package com.screentimex.notes.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.screentimex.notes.Models.Notes
import com.screentimex.notes.R
import com.screentimex.notes.ViewModel.NotesViewModel
import com.screentimex.notes.databinding.FragmentHomeBinding
import com.screentimex.notes.ui.Adapter.NotesAdapter
import java.util.Objects

class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter : NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        homeBinding.notesrecyclerview.layoutManager = staggeredGridLayoutManager

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            homeBinding.notesrecyclerview.adapter = adapter
        }

        homeBinding.apply {
            filterHigh.setOnClickListener {
                setToolbarTitle("High Priority")
                viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    homeBinding.notesrecyclerview.adapter = adapter
                }
            }
            filterMedium.setOnClickListener {
                setToolbarTitle("Medium Priority")
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    homeBinding.notesrecyclerview.adapter = adapter
                }
            }
            filterLow.setOnClickListener {
                setToolbarTitle("Low Priority")
                viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    homeBinding.notesrecyclerview.adapter = adapter
                }
            }
            allNotes.setOnClickListener{
                setToolbarTitle("Home")
                viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    homeBinding.notesrecyclerview.adapter = adapter
                }
            }
        }

        homeBinding.addButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return homeBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }
        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for ( i in oldNotes ){
            if ( i.title?.contains(p0!!) == true || i.subtitle?.contains(p0!!) == true) {
                newFilteredList.add(i)
            }
        }
        adapter.filttering(newFilteredList)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Home")
    }

    private fun setToolbarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }



}