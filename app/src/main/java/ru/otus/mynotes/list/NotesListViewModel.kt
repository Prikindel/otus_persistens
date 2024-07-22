package ru.otus.mynotes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.otus.mynotes.NotesRepository
import ru.otus.mynotes.references.DataStoreProvider

class NotesListViewModel(
    private val repository: NotesRepository = NotesRepository,
    private val preferences: DataStoreProvider = DataStoreProvider
) : ViewModel() {
    private val _viewState : MutableStateFlow<NotesListViewState> =
        MutableStateFlow(
            NotesListViewState(
                notes = emptyList(),
                columnCount = 2
            )
        )

    val viewState: Flow<NotesListViewState> get() = _viewState

    init {
        viewModelScope.launch {
            preferences.getColumnCount().collect { count ->
                _viewState.value = _viewState.value.copy(columnCount = count)
            }
        }

        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            repository.getAllNotes()
                .collect { notes ->
                    _viewState.value = _viewState.value.copy(notes = notes)
                }
        }
    }

    fun onColumnCountChanged(count: Int) {
        _viewState.value = _viewState.value.copy(columnCount = count)
        viewModelScope.launch {
            preferences.setColumnCount(count)
        }
    }

    fun onViewResumed() {
        loadNotes()
    }
}