package ru.otus.mynotes.references

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import ru.otus.mynotes.NotesApplication

private const val DATASTORE_FILE = "datastore_file"
private const val KEY_COLUMN_COUNT_DATA_STORE = "key_column_count_data_store"
private const val DEF_COLUMN_COUNT = 2
private val keyColumnCount = intPreferencesKey(KEY_COLUMN_COUNT_DATA_STORE)

object DataStoreProvider {

    private val dataStore = NotesApplication.context.settingsDataStore

    suspend fun setColumnCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[keyColumnCount] = count
        }
    }

    fun getColumnCount(): Flow<Int> = dataStore.data.mapNotNull { preferences ->
        preferences[keyColumnCount] ?: DEF_COLUMN_COUNT
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
//            it.remove(keyColumnCount)
        }
    }
}

val Context.settingsDataStore by preferencesDataStore(
    name = DATASTORE_FILE,
    produceMigrations = { context -> listOf(createMigration(context)) }
)

fun createMigration(context: Context) =
    SharedPreferencesMigration(
        context = context,
        sharedPreferencesName = SHARED_PREFS,
        keysToMigrate = setOf(KEY_COLUMN_COUNT)
    )