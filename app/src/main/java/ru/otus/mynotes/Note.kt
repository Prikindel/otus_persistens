package ru.otus.mynotes

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.otus.mynotes.Note.Companion.TABLE_NAME
import java.util.Date

@Entity(tableName = TABLE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val text: String,
    val date: Date
) {
    companion object {
        const val TABLE_NAME = "note"
    }
}