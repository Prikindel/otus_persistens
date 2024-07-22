package ru.otus.mynotes.references

import android.content.Context
import androidx.core.content.edit
import ru.otus.mynotes.NotesApplication

private const val SHARED_PREFS = "shared_prefs"
private const val KEY_COLUMN_COUNT = "key_column_count"
private const val DEF_COLUMN_COUNT = 2

object SharedPreferencesProvider {
    private val preferences = NotesApplication.context.getSharedPreferences(
        SHARED_PREFS,
        Context.MODE_PRIVATE
    )

    fun setColumnCount(count: Int) {
        preferences.edit {
            putInt(KEY_COLUMN_COUNT, count)
        }
    }

    fun getColumnCount() = preferences.getInt(KEY_COLUMN_COUNT, DEF_COLUMN_COUNT)

    fun clear() {
        preferences.edit {
//            remove(KEY_COLUMN_COUNT)
            clear()
        }
    }
}