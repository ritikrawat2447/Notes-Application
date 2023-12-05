package com.screentimex.notes.Repository

import androidx.lifecycle.LiveData
import com.screentimex.notes.Dao.NotesDao
import com.screentimex.notes.Models.Notes

class NotesRepository(val dao : NotesDao) {
    fun getNotes() : LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun getHighNotes() : LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes() : LiveData<List<Notes>> = dao.getLowNotes()
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id : Int){
        dao.deleteNodes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}