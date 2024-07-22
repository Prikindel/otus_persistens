package ru.otus.mynotes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.otus.mynotes.Note
import ru.otus.mynotes.oldNotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.otus.mynotes.NotesRepository

class NotesListViewModel(
    private val repository: NotesRepository = NotesRepository
) : ViewModel() {
    private val _viewState : MutableStateFlow<NotesListViewState>

    val viewState: Flow<NotesListViewState> get() = _viewState

    init {
        val state = NotesListViewState(
            notes = emptyList(),
            columnCount = 2
        )

        _viewState = MutableStateFlow(state)
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
    }

    fun onViewResumed() {
        loadNotes()
    }
}