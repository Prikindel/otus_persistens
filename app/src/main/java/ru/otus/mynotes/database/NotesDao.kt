package ru.otus.mynotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.otus.mynotes.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM ${Note.TABLE_NAME}")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM ${Note.TABLE_NAME} WHERE id = :id")
    suspend fun getNote(id: Long): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)
}