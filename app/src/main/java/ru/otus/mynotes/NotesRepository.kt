package ru.otus.mynotes

import kotlinx.coroutines.flow.Flow
import ru.otus.mynotes.database.NotesDao
import ru.otus.mynotes.database.NotesDb
import java.util.Date

//class NotesRepository @Inject constructor(
//    private val storage: NotesDb
//)

object NotesRepository {

    private val storage: NotesDao = NotesApplication.db.notesDao()

    fun getAllNotes(): Flow<List<Note>> = storage.getAllNotes()

    suspend fun create(title: String, note: String) {
        if (title.isBlank() && note.isBlank()) return

        val newNote = Note(
            id = 0,
            title = title,
            text = note,
            date = Date()
        )
        storage.insert(newNote)
    }

    suspend fun update(note: Note) {
        storage.update(note)
    }

    suspend fun get(id: Long): Note? = storage.getNote(id)
}